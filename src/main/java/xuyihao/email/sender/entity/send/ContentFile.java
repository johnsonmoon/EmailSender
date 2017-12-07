package xuyihao.email.sender.entity.send;

/**
 * file in the email message content, includes contentId and contentFilePathName
 * <p>
 * Created by xuyh at 2017/12/7 14:52.
 */
public class ContentFile {
	private String contentId;
	private String contentFilePathName;

	public static ContentFile create() {
		return new ContentFile();
	}

	/**
	 * @param contentId           contentId in the content
	 * @param contentFilePathName content file path name in the disk
	 */
	public static ContentFile create(String contentId, String contentFilePathName) {
		return new ContentFile(contentId, contentFilePathName);
	}

	public ContentFile() {
	}

	/**
	 * @param contentId           contentId in the content
	 * @param contentFilePathName content file path name in the disk
	 */
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
