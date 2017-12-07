# EmailSender
Email tools for java.

Using java email API [javax.mail] to send email by SMTP protocol

## How to use:

### 1.Get ready

##### (1)Clone the project to local computer.

##### (2)Make sure your computer is ready for the environment of java8 and maven building tools.

##### (3)Locate to the project working directory.

##### (4)Execute command: **[mvn install -Dmaven.test.skip=true]**.(maven and jdk needed)

##### (5)Found the .jar file in directory [target].

##### (6)Import the .jar file into your project classpath.

### 2.Dependencies
maven dependencies
```
<dependency>
	<groupId>com.sun.mail</groupId>
	<artifactId>javax.mail</artifactId>
	<version>1.5.6</version>
</dependency>
```

### 3.Use the tools

#### examples:

###### (1)Send text message

```
EmailSender.send(
        EmailSender.createCredential("host", 25, "user", "password"),
        EmailSender.createTextMessage("from", new String[]{"to"}, "subject", "content")
);
```


##### (2)Send multimedia message with attachment file.

```
EmailSender.send(
        EmailSender.createCredential("host", 25, "user", "password"),
        EmailSender.createMultiMediaMessage("from", new String[]{"to"}, "subject", Arrays.asList("attachFilepathName1", "attachFilePath2"),
                "contentText", new ArrayList<>())
);
```

