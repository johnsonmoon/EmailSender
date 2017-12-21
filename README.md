# EmailSender
Email tools for java.

Using java email API [javax.mail] to send email by SMTP, POP3 and IMAP.

## How to use:

### 1.Get ready

##### (1)Clone the project to local computer.

##### (2)Make sure your computer is ready for the environment of java8 and maven building tools.

##### (3)Locate to the project working directory.

##### (4)Execute command: **[mvn install -Dmaven.test.skip=true]**.(maven and jdk needed)

##### (5)Found the .jar file in directory [target].

##### (6)Import the .jar file into your project classpath.


### 2.Use the tools

### add maven dependency like this:
```
<dependency>
	<groupId>xuyihao</groupId>
	<artifactId>email-sender</artifactId>
	<version>1.0</version>
	<scope>system</scope>
	<systemPath>${basedir}/src/main/lib/email-sender-1.0.jar</systemPath>
</dependency>

<dependency>
	<groupId>com.sun.mail</groupId>
	<artifactId>javax.mail</artifactId>
	<version>1.5.6</version>
</dependency>
```

#### examples:

###### (1)Send text message

```
EmailCredential credential = new EmailCredential(${emailHost}, ${portNumber}, ${protocol}, ${userAccount}, ${userAccountPassword});
EmailTextMessage textMessage = new EmailTextMessage(${senderAddress}, ${receiverAddressArray}, ${subjectOfTheEmail}, ${contentTextMessage});
EmailUtils.sendTextEmail(credential, textMessage);
```


##### (2)Send multimedia message with attachment file.

```
EmailCredential credential = new EmailCredential(${emailHost}, ${portNumber}, ${protocol}, ${userAccount}, ${userAccountPassword});
EmailMultiMediaMessage multiMediaMessage = new EmailMultiMediaMessage();
multiMediaMessage.setFrom(${senderAddress});
multiMediaMessage.setTo(new String[] { ${receiverAddressArray} });
multiMediaMessage.setSubject(${subjectOfTheEmail});
multiMediaMessage.setContentText("<html>\n" +
        "\t<head>\n" +
        "\t\t<title>test email with files</title>\n" +
        "\t</head>\n" +
        "\n" +
        "\t<body>\n" +
        "\t\t<pre>\n" +
        "\t\t\tHello\n" +
        "\t\t\t<img src = \"cid:${id_1_contentFile}\">\n" +
        "\t\t\t<img src = \"cid:${id_2_contentFile}\">\n" +
        "\t\t\t<img src = \"cid:${id_3_contentFile}\">\n" +
        "\t\t</pre>\n" +
        "\t</body>\n" +
        "</html>");
multiMediaMessage.addContentFile(${id_1_contentFile}, ${contentFilePath_1});
multiMediaMessage.addContentFile(${id_2_contentFile}, ${contentFilePath_2});
multiMediaMessage.addContentFile(${id_3_contentFile}, ${contentFilePath_3});
multiMediaMessage.addAttachFile(${attachmentFilePath_1});
multiMediaMessage.addAttachFile(${attachmentFilePath_2});
multiMediaMessage.addAttachFile(${attachmentFilePath_3});
EmailUtils.sendMultiMediaEmail(credential, multiMediaMessage);
```

