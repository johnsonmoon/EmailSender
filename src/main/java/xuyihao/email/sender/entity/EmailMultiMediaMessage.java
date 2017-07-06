package xuyihao.email.sender.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Message details for sending email. (MultiMedia Message)
 *
 * Created by xuyh at 2017/7/6 16:53.
 */
public class EmailMultiMediaMessage extends EmailMessage {
	/**
	 * Attach files for email message.
	 */
	private List<String> attachFilePathNameList;
	/**
	 * Text message for the content.
	 */
	private String contentText;
	/**
	 * File for the content.
	 */
	private List<ContentFile> contentFileList;

	public EmailMultiMediaMessage() {
	}

	public EmailMultiMediaMessage(String from, String[] to, String subject, List<String> attachFilePathNameList,
			String contentText, List<ContentFile> contentFileList) {
		super(from, to, subject);
		this.attachFilePathNameList = attachFilePathNameList;
		this.contentText = contentText;
		this.contentFileList = contentFileList;
	}

	public List<String> getAttachFilePathNameList() {
		return attachFilePathNameList;
	}

	public void setAttachFilePathNameList(List<String> attachFilePathNameList) {
		this.attachFilePathNameList = attachFilePathNameList;
	}

	public String getContentText() {
		return contentText;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText;
	}

	public List<ContentFile> getContentFileList() {
		return contentFileList;
	}

	public void setContentFileList(List<ContentFile> contentFileList) {
		this.contentFileList = contentFileList;
	}

	/**
	 * Add an attach file.
	 *
	 * @param filePathName
	 */
	public void addAttachFile(String filePathName) {
		if (attachFilePathNameList == null)
			attachFilePathNameList = new ArrayList<>();
		attachFilePathNameList.add(filePathName);
	}

	/**
	 * Remove an attach file
	 *
	 * @param filePathName
	 */
	public void removeAttachFile(String filePathName) {
		if (attachFilePathNameList == null)
			return;
		if (attachFilePathNameList.contains(filePathName))
			attachFilePathNameList.remove(filePathName);
	}

	/**
	 * Add an content file.(Could be image, etc.)
	 * <pre>
	 *     Should be id-filePathName. 
	 * </pre>
	 * 
	 * @param contentId
	 * @param contentFilePathName
	 */
	public void addContentFile(String contentId, String contentFilePathName) {
		if (contentFileList == null)
			contentFileList = new ArrayList<>();
		contentFileList.add(new ContentFile(contentId, contentFilePathName));
	}

	/**
	 * Remove an content file.
	 * <pre>
	 *     Should be id-filePathName. 
	 * </pre>
	 *
	 * @param contentId
	 * @param contentFilePathName
	 */
	public void removeContentFile(String contentId, String contentFilePathName) {
		if (contentFileList == null)
			return;
		ContentFile contentFile = new ContentFile(contentId, contentFilePathName);
		if (contentFileList.contains(contentFile))
			contentFileList.remove(contentFile);
	}

	public class ContentFile {
		private String contentId;
		private String contentFilePathName;

		public ContentFile() {
		}

		public ContentFile(String contentId, String contentFilePathName) {
			this.contentId = contentId;
			this.contentFilePathName = contentFilePathName;
		}

		public String getContentId() {
			return contentId;
		}

		public void setContentId(String contentId) {
			this.contentId = contentId;
		}

		public String getContentFilePathName() {
			return contentFilePathName;
		}

		public void setContentFilePathName(String contentFilePathName) {
			this.contentFilePathName = contentFilePathName;
		}
	}
}
