Assignment 1



Authors: Elena Ibi, Nour Oujjit, Wassim Mayyasi, Ørjan Johnsen

# Introduction

Author(s): Elena Ibi, Wassim Mayyasi



Imagine the scenario where you constantly work on similar programming projects and you have so many code snippets that you have to reuse over and over again. Wouldn’t rewriting them multiple times be an annoying tedious task? What if there was a system you could use to save all your code snippets and be able to retrieve them easily whenever needed? The solution to this problem is the code snippet manager which is the project track we chose to go for. A code snippet manager is a very useful tool that programmers and other users can use to keep a record of code snippets and libraries for reusability purposes. 



Additionally, the code snippet manager will provide a well-structured and organized system to keep record of all the code snippets that the user will add; this makes the search and update functions easy for the user. Our code snippet manager will allow the user to display, sort the code based on some specified attributes, mark snippets as favorite snippets, copy the code of the snippet onto clipboard aside from providing the regular update functions such as adding and deleting a snippet through our user-friendly graphical interface. 



Our target users are any users who work with code on a frequent basis. It does not get any more specific than this since we believe that it is such an important tool that anyone working with code, ranging from professional programmers to aspirant programmers, can benefit from. Furthermore, the graphical user interface that our system will have will make the navigation of the code snippet manager very easy and user-friendly; it will display all the important buttons that the user can click on to have access to the various functions, display all the snippets that have been added so far on the front page. Generally speaking, we thought that a user-friendly interface was one of the most important aspects for systems such as code snippet managers because of the ease it should provide in the retrieval process. Hence, we decided to implement a graphical user interface in the intent to facilitate the navigation and the general use of the code snippet manager for every type of user. 



The decision to implement the code snippet manager was taken because we thought that a way to organize and store code snippets was a useful activity in the long run for anyone who works with code. It stood out as a project to us since it did not tackle the more commonly chosen projects that implement games or similar types of applications that deal more with entertainment but rather provided a useful functionality for anyone who works with code at any level and is useful for the development of any other type of software. There were few sources online we took inspiration from such as this youtube video which gave us an excellent overview of not only what a code snippet manager should do but also an idea for a possible graphical user interface. The link to this youtube video is the following:

https://www.youtube.com/watch?v=lfWRwwmX9Q8

It is a 2 minutes and 30 seconds video that highlights the important features of an already existing code snippet manager, ‘Code Notes’. 

Another source of inspiration is the following website:

https://dev.to/tomlangdon/5-code-snippet-managers-that-will-change-the-way-you-write-code-10ml

This website underlines all the general pros and cons of using such a system and provides a similar overview on different code snippet managers; it mentions five code snippet managers that are widely used worldwide. The similarities and the differences of these code snippet managers are mentioned which helped us gain more knowledge on these systems and gave us a wider perspective design wise. We also thought of some future improvements that the existing code snippet managers could adopt. 



Our goal of the final design of this project is an application for individuals who come in contact with programs or code. Those individuals will be able to use this application to store code snippets that they feel are important and possibly reusable in the future. They would add their chosen snippet into the text portion of the application, then add a name they think is appropriate, tags they think are useful to add and search up later, and the programming language the code is written in. All these characteristics or attributes will be used for the user to later extract the needed snippet from the collection of snippets added. There is an editing option as well, it could be used for any mistakes made while adding the snippet, or possibly for updating the code with a better one made. The user will also have a search bar with different options, for example, searching the code snippets currently stored with a certain attribute. In the front page, there will be a display of all the stored snippets, they will be displayed by their date of creation, however, the user can alter this sorting order to different options.There will also be some buttons at the top of the page for editing and deleting the selected snippet. When deleting a snippet, there will be a reassurance message for the user just in case of misclicks. Finally, after the user chooses their code snippet, the code snippet will be available on display, and a possible button to copy the code snippet to clipboard is present; that will allow the user to use this code snippet in whatever project they are currently working on.



In the back end, the code snippets will be stored in text files, and the descriptions and attributes of each snippet will be stored in a JSON file. When a certain code snippet is chosen for display, what happens is that the file name of the snippet will be stored in the descriptions of that snippet in the JSON file, so that it has to get extracted first, then access to that file is made to retrieve the actual snippet.



# Features

Author(s) : Elena Ibi

### Functional features

When thinking about the functionality of the final application and its usage, we believe the adding and deleting features to be essential. As for the searching and displaying, we believe those are necessities because they allow the user to manage the snippets, without them, this application would not be a manager, but a snippet storage. The “should-have” features are not essentials of the system, for example, the editing feature, as useful as it is, its functionality is not essential to make the application purposeless without it. The same holds for copying, favorites, and sorting as they all help make the application more useful, and easier to use, however, the code snippet manager can exist without them. 



| Id   | Name       | Description                                                  |
| ---- | ---------- | ------------------------------------------------------------ |
| F1   | Adding     | The system shall enable the user to add a snippet. This will be done by providing the user with a template on which the name, description, tags, programming language and content of the snippet can be filled in.  Rationale: the user will be able to store snippets they feel could be useful later. |
| F2   | Searching  | The system shall allow the user to search for specific snippets based on three options: name, tags and the code language. This function will output a subset of snippets based on users' search quirie. Rationale: the user will be able to browse through the snippets with a specific keyword to find the snippet they were searching for. |
| F3   | Editing    | The user should be able to alter the name, description, tag, language and the content of already existing snippets. This is done with a similar template to the one for adding snippets.  Rationale: the user can fix any mistakes, big or small, with any of the attributes of a snippet easily. |
| F4   | Displaying | The system shall display all or a subset of the stored snippets. The snippets will be displayed in a list format, when a snippet is chosen, the snippet shall be displayed with its attributes. Rationale: Allows the user to view and access the snippets they stored. |
| F5   | Deleting   | The system shall enable the user to delete a snippet by providing a delete button next to every snippet that the user can press after selecting the snippet to be deleted.Rationale: the user is allowed to get rid of snippets they deem useless. |
| F6   | Sorting    | The system should allow sorting of the displayed snippets. The user can choose to sort the snippets based on the name, language or date of the snippet (default).  Rationale: to help the user easily find code snippets of the same language. Also, they can sort by name and date if the user does not remember the exact snippet they are looking for. |
| F7   | Favorites  | The system should enable the user to mark snippets as favorites. The user will be able to do so by pressing a button next to the displayed snippet. The system provides the option to display only favorited snippets.  Rationale: the user can choose favorite snippets to keep very accessible at all time, might be the frequently used ones, it is up to the user. |
| F8   | Copying    | The system should provide a copy button that will enable the user to copy the code of the snippet onto clipboard. Rationale: to help users extract their code snippet quickly without having to open it first. |



### Quality requirements

Authors: Ørjan Johnsen, Nour Oujjit



Several quality requirements are required to develop a user friendly snippet manager. For each constraint the rational, eventual alternatives, as well as a short description will be discussed in the table below. 

 

| Id   | Name              | Quality attribute | Description                                                  |
| ---- | ----------------- | ----------------- | ------------------------------------------------------------ |
| QR1  | Consistent Style  | Maintainability   | System code should follow one consistent code convention. The code convention to be used is specified by: [oracle convention](https://www.oracle.com/technetwork/java/codeconventions-150003.pdf) Rationale: guarantees a homogeneous look of code. Code written by different members should follow the same style to ensure readability. This makes it easily maintainable. |
| QR2  | Code Separation   | Maintainability   | Code dealing with program logic and code handling the UI shall be kept separate from each other (Using MVC pattern). Rationale: in order to spare maintenance time classes and resources should be divided up. The different types of sources can be located faster this way. |
| QR3  | View Controllers  | Maintainability   | Every view shall have its own controller class.  Rationale: it reduces maintenance time by making it easier to find the variables corresponding to the GUI components of the different views. |
| QR4  | User Confirmation | Reliability       | The system shall not add, remove or alter a snippet before the user affirms confirmation message. Rationale: to prevent accidental alteration of snippet data. Ensures that the user is sure of performed action. Alternative: We considered storing deleted snippets and discarding them definitely after a time period allowing the user to revert deleted snippets. However, the confirmation message proved sufficient. |
| QR5  | Termination Check | Reliability       | Before the system terminates it should check for unfinished/unsaved snippets and ask the user whether to save or discard before closing. Rationale: this would ensure that the user does not close the window on a half finished snippet out of forgetfulness.  Alternative: We considered writing the snippet data directly onto the json file and code file as it is typed by the user. However, writing to text-files directly showed to be really difficult for now, but maybe for a future bigger system this would be implemented. |
| QR6  | Descriptive Text  | Usability         | The text fields and prompt text used by the system shall be short and descriptive.  Rationale: This way the user can easily navigate throughout the system without getting confused about how to perform particular tasks. Alternative: help function, this was refuted to maintain the simplicity of the system. System should be simple enough to be understood intuitively. |
| QR7  | Empty Fields      | Usability         | System shall give user feedback in case of empty text fields before saving snippets. Rationale: This constraint is important to avoid missing important snippet information that will later be needed when performing searching/ sorting. Alternative: saving button can be made inactive until all fields have been filled. This was not picked because it doesn’t specify to the user which field is left unfilled, reducing usability. |
| QR8  | Local Resources   | Availability      | System shall be available at all times. Rationale: since it runs entirely on the local system and needs no remote resources to perform its functionality. |
| QR9  | System Response   | Responsiveness    | The system should respond to interactions with the user within one second. This applies to basic functionalities as well as ones loading JSON data.  Rationale: It is important for the system to load snippets fast since the entire goal of having a snippet manager is the fast and easy access to snippets of code. If the responsiveness is bad the user might as well use an ordinary file manager to look up and open code. |



### 

### Java libraries



Author(s): Wassim Mayyasi, Nour Oujjit



| Name                                                         | Description                                                  |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [JasonSimple](https://github.com/fangyidong/json-simple)     | This library contains the functions needed to parse the contents of a json file into a JSONObject object. We will need this object to add new snippets or alter / delete existing ones. Snippets are represented as json objects nested within a json array with key snippets. |
| [gson](https://github.com/google/gson)                       | This library will be used to convert java objects into JSONObjects and vice versa. This library has been chosen from amongst others because of its very easy to use interface. Just one function call converts a java class, using its attributes as json keys and their contents as the values. |
| [JsonPath](https://github.com/json-path/JsonPath)            | We plan on storing the data of the snippets within a Json file. JsonPath is a library that enables the retrieval of JSON data based on the use of specific queries. This library will be useful to realize the searching, filtering, favorites and displaying functionality of the system. We can, for example, extract the data of all snippets that are marked as favorites. |
| [Scene Builder](https://github.com/gluonhq/scenebuilder)     | Scene Builder is a UI designer tool that has a drag and drop functionality. We chose this tool because we did not want to waste too much time on programming the UI, and using this tool makes it easier to have the UI we imagined without too much effort. Scene Builder works with the JavaFX library. |
| [JavaFX](https://github.com/openjdk/jfx)                     | JavaFX is a library that will provide us with all resources needed to develop a graphical user interface for our application. The Scene Builder tool works alongside the JavaFX library, so it was needed after the decision to use Scene Builder was made. |
| [JFoenix](https://github.com/jfoenixadmin/JFoenix)           | JFoenix will be used to extend the GUI elements provided by JavaFX. This would give us more choice in controls and allow us to use the most user friendly ones. |
| [Java Standard](https://docs.oracle.com/javase/7/docs/api/index.html) | The java standard library has several packages that will have multiple uses for our project. Starting with the java.awt; It contains the needed classes/functions for us to copy to the clipboard for our feature(F9). Next, there is the java.lang; for uses of different data types throughout our project, as Java does not have those data types as primitive, but as classes. Finally, there is the java.util, the utilities package. Using this package, we will have access to Lists and Arrays, to possibly parse the json arrays to java arrays. Also, then we can use the sorting function that is available in this package as well to sort those arrays as needed for our feature(F7). The last usage of this package is the availability of Date and Time classes; we can use those classes to add the attribute of “date and time added” to each snippet. |