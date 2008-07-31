/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portlet.webform.action;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.documentlibrary.FileNameException;
import com.liferay.documentlibrary.FileSizeException;
import com.liferay.documentlibrary.service.DLLocalServiceUtil;
import com.liferay.mail.service.MailServiceUtil;
import com.liferay.portal.captcha.CaptchaTextException;
import com.liferay.portal.captcha.CaptchaUtil;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletConfigImpl;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import com.liferay.portlet.webform.service.WebFormLocalServiceUtil;
import com.liferay.portlet.webform.util.WebFormUtil;
import com.liferay.util.PwdGenerator;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.mail.internet.InternetAddress;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <a href="ViewAction.java.html"><b><i>View Source</i></b></a>
 *
 * @author Daniel Weisser
 * @author Jorge Ferrer
 * @author Alberto Montero
 *
 */
public class ViewAction extends PortletAction {

	public void processAction(
			ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		PortletConfigImpl portletConfigImpl = (PortletConfigImpl)portletConfig;

		String portletId = portletConfigImpl.getPortletId();

		PortletPreferences prefs =
			PortletPreferencesFactoryUtil.getPortletSetup(
				actionRequest, portletId);

		boolean requireCaptcha = GetterUtil.getBoolean(
			prefs.getValue("requireCaptcha", StringPool.BLANK));
		String successURL = GetterUtil.getString(
			prefs.getValue("successURL", StringPool.BLANK));
		boolean sendAsEmail = GetterUtil.getBoolean(
			prefs.getValue("sendAsEmail", StringPool.BLANK));
		boolean saveToDatabase = GetterUtil.getBoolean(
			prefs.getValue("saveToDatabase", StringPool.BLANK));
		String databaseTableName = GetterUtil.getString(
			prefs.getValue("databaseTableName", StringPool.BLANK));
		boolean saveToFile = GetterUtil.getBoolean(
			prefs.getValue("saveToFile", StringPool.BLANK));
		String fileName = GetterUtil.getString(
			prefs.getValue("fileName", StringPool.BLANK));
		boolean uploadToDL = GetterUtil.getBoolean(
			prefs.getValue("uploadToDL", StringPool.BLANK));
		boolean uploadToDisk = GetterUtil.getBoolean(
			prefs.getValue("uploadToDisk", StringPool.BLANK));
		String uploadDiskDir = GetterUtil.getString(
			prefs.getValue("uploadDiskDir", StringPool.BLANK));

		if (uploadToDisk) uploadToDisk = Validator.isNotNull(uploadDiskDir);

		if (requireCaptcha) {
			try {
				CaptchaUtil.check(actionRequest);
			}
			catch (CaptchaTextException cte) {
				SessionErrors.add(
					actionRequest, CaptchaTextException.class.getName());

				return;
			}
		}

		List<String> fieldValues = new ArrayList<String>();

		UploadPortletRequest uploadReq = null;

		for (int i = 1; i <= WebFormUtil.MAX_FIELDS; i++) {
			String fieldType = prefs.getValue("fieldType" + i, null);

			if ("file".equals(fieldType)) {
				if (uploadReq == null) uploadReq = PortalUtil.getUploadPortletRequest(actionRequest);

				String uploadFileName = uploadReq.getFileName("field" + i);
				if (Validator.isNotNull(uploadFileName)) {
					uploadFileName = PwdGenerator.getPassword(4) + "_" + uploadFileName;
				}
				fieldValues.add(uploadFileName);
			}
			else {
				fieldValues.add(actionRequest.getParameter("field" + i));
			}
		}

		Set<String> validationErrors = null;

		try {
			validationErrors = validate(fieldValues, prefs);
		}
		catch (Exception e) {
			actionRequest.setAttribute(
					"validationScriptError", e.getMessage().trim());

			setForward(actionRequest, "portlet.web_form.error");

			return;
		}

		if (validationErrors.isEmpty()) {
			boolean uploadSuccess = true;
			boolean emailSuccess = true;
			boolean databaseSuccess = true;
			boolean fileSuccess = true;

			if (uploadReq != null) {
				try {
					for (int i = 1; i <= fieldValues.size(); i++) {
						String fieldType = prefs.getValue("fieldType" + i, null);

						if ("file".equals(fieldType)) {
							File uploadFile = uploadReq.getFile("field" + i);
							String uploadFileName = fieldValues.get(i - 1);

							if (uploadFile != null && Validator.isNotNull(uploadFileName)) {
								if (uploadToDL || uploadToDisk) {
									byte[] bytes = FileUtil.getBytes(uploadFile);

									if (uploadToDL) {
										WebFormLocalServiceUtil.storeUploadedFile(PortalUtil.getCompany(actionRequest).getCompanyId(), portletId, uploadFileName, bytes);
									}

									if (uploadToDisk) {
										DLLocalServiceUtil.validate(uploadFileName, uploadFile);
										FileUtil.write(new File(uploadDiskDir + "/" + uploadFileName), bytes);
									}
								}
								else {
									throw new Exception();
								}
							}
						}
					}
				}
				catch(FileNameException e) {
					SessionErrors.add(actionRequest, "uploadFileNameIllegal");
					uploadSuccess = false;
				}
				catch(FileSizeException e) {
					SessionErrors.add(actionRequest, "uploadFileTooLarge");
					uploadSuccess = false;
				}
				catch(Exception e) {
					SessionErrors.add(actionRequest, "uploadFailed");
					uploadSuccess = false;
				}
			}

			if (uploadSuccess) {
				if (sendAsEmail) {
					emailSuccess = sendEmail(fieldValues, prefs);
				}

				if (saveToDatabase) {
					if (Validator.isNull(databaseTableName)) {
						databaseTableName = WebFormUtil.getNewDatabaseTableName(
							portletId);

						prefs.setValue("databaseTableName", databaseTableName);

						prefs.store();
					}

					databaseSuccess = saveDatabase(
						fieldValues, prefs, databaseTableName);
				}

				if (saveToFile) {
					fileSuccess = saveFile(fieldValues, prefs, fileName);
				}
			}

			if (uploadSuccess && emailSuccess && databaseSuccess && fileSuccess) {
				SessionMessages.add(actionRequest, "success");
			}
			else {
				SessionErrors.add(actionRequest, "error");
			}
		}
		else {
			for (String badField : validationErrors) {
				SessionErrors.add(actionRequest, "error" + badField);
			}
		}

		if (SessionErrors.isEmpty(actionRequest) &&
			Validator.isNotNull(successURL)) {

			actionResponse.sendRedirect(successURL);
		}
		else {
			sendRedirect(actionRequest, actionResponse);
		}
	}

	public ActionForward render(
		ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		return mapping.findForward(
			getForward(renderRequest, "portlet.web_form.view"));
	}

	protected String getMailBody(
		List<String> fieldValues, PortletPreferences prefs) {

		StringBuilder sb = new StringBuilder();

		Iterator<String> itr = fieldValues.iterator();

		for (int i = 1; itr.hasNext(); i++) {
			String fieldValue = itr.next();

			String fieldLabel = prefs.getValue(
				"fieldLabel" + i, StringPool.BLANK);

			if (Validator.isNotNull(fieldLabel)) {
				sb.append(fieldLabel);
				sb.append(" : ");
				sb.append(fieldValue);
				sb.append("\n");
			}
		}

		return sb.toString();
	}

	private boolean saveDatabase(
			List<String> fieldValues, PortletPreferences prefs,
			String databaseTableName)
		throws Exception {

		WebFormUtil.checkTable(databaseTableName, prefs);

		long classPK = CounterLocalServiceUtil.increment(
			WebFormUtil.class.getName());

		Iterator<String> itr = fieldValues.iterator();

		try {
			for (int i = 1; itr.hasNext(); i++) {
				String fieldValue = itr.next();

				String fieldLabel = prefs.getValue(
					"fieldLabel" + i, StringPool.BLANK);

				if (Validator.isNotNull(fieldLabel)) {
					ExpandoValueLocalServiceUtil.addValue(
						WebFormUtil.class.getName(), databaseTableName,
						fieldLabel, classPK, fieldValue);
				}
			}

			return true;
		}
		catch (Exception e) {
			_log.error(
				"The web form data could not be saved to the database", e);

			return false;
		}
	}

	protected boolean saveFile(
		List<String> fieldValues, PortletPreferences prefs, String fileName) {

		// Save the file as a standard Excel CSV format. Use ; as a delimiter,
		// quote each entry with double quotes, and escape double quotes in
		// values a two double quotes.

		StringBuilder sb = new StringBuilder();

		Iterator<String> itr = fieldValues.iterator();

		for (int i = 1; itr.hasNext(); i++) {
			String fieldValue = itr.next();

			String fieldLabel = prefs.getValue(
				"fieldLabel" + i, StringPool.BLANK);

			if (Validator.isNotNull(fieldLabel)) {
				sb.append("\"");
				sb.append(StringUtil.replace(fieldValue, "\"", "\"\""));
				sb.append("\";");
			}
		}

		String s = sb.substring(0, sb.length() - 1) + "\n";

		try {
			FileUtil.write(fileName, s, false, true);

			return true;
		}
		catch (Exception e) {
			_log.error("The web form data could not be saved to a file", e);

			return false;
		}
	}

	protected boolean sendEmail(
		List<String> fieldValues, PortletPreferences prefs) {

		try {
			String subject = prefs.getValue("subject", StringPool.BLANK);
			String emailAddress = prefs.getValue(
				"emailAddress", StringPool.BLANK);

			if (Validator.isNull(emailAddress)) {
				_log.error(
					"The web form email cannot be sent because no email " +
						"address is configured");

				return false;
			}

			String body = getMailBody(fieldValues, prefs);

			InternetAddress fromAddress = new InternetAddress(emailAddress);
			InternetAddress toAddress = new InternetAddress(emailAddress);

			MailMessage mailMessage = new MailMessage(
				fromAddress, toAddress, subject, body);

			MailServiceUtil.sendEmail(mailMessage);

			return true;
		}
		catch (Exception e) {
			_log.error("The web form email could not be sent", e);

			return false;
		}
	}

	protected Set<String> validate(
			List<String> fieldValues, PortletPreferences prefs)
		throws Exception {

		Set<String> validationErrors = new HashSet<String>();

		for (int i = 1; i < WebFormUtil.MAX_FIELDS; i++) {
			String fieldValue = fieldValues.get(i - 1);

			String fieldLabel = prefs.getValue(
				"fieldLabel" + i, StringPool.BLANK);
			boolean fieldOptional = GetterUtil.getBoolean(
				prefs.getValue("fieldOptional" + i, StringPool.BLANK));

			if (!fieldOptional && Validator.isNotNull(fieldLabel) &&
				Validator.isNull(fieldValue)) {

				validationErrors.add(fieldLabel);

				continue;
			}

			String validationScript = GetterUtil.getString(
				prefs.getValue("fieldValidationScript" + i, StringPool.BLANK));

			if (Validator.isNotNull(validationScript) &&
				!WebFormUtil.validate(
					fieldValue, fieldValues, validationScript)) {

				validationErrors.add(fieldLabel);

				continue;
			}
		}

		return validationErrors;
	}

	protected boolean isCheckMethodOnProcessAction() {
		return _CHECK_METHOD_ON_PROCESS_ACTION;
	}

	private static final boolean _CHECK_METHOD_ON_PROCESS_ACTION = false;

	private static Log _log = LogFactory.getLog(ViewAction.class);

}
