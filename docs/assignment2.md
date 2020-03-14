# Assignment 2



# Implemented Feature

| **ID** | **Short Name** | **Description**                                              |
| ------ | -------------- | ------------------------------------------------------------ |
| F1     | Adding         | The system shall enable the user to add a snippet. This will be done by providing the user with a template on which the name, description, tags, code language and content of the snippet can be filled in. |

##### Modelling tool used: draw.io



# Class Diagram

Author(s): Nour Oujjit

The current version of this diagram is fully compatible with the current system. All elements that were needed for the implementation during the second phase of the project are present in detail. Most of the structure for the next phase is already implemented, which means that the diagram presented below covers all of the systems functionality. The most common associations between the classes in the diagram is *dependency*. The main reason for this is the **SnippetManagerClass** having all static members. 

The libraries added to the system as well as the fact that it is GUI based, simplified the class diagram needed to represent the system. Since a lot of the functionality is offered by these libraries. The **Snippet** class seems deep at first sight but in reality most of its operations are getters and setters. We have considered splitting up the **SnippetDataManager** class because it seemed rather large. The reasoning behind not doing this is because the operations are related in function (namely handling snippet JSON data) and all operate using the same variable.

The current UML class diagram is depicted below. It is made up of three types of classes: Library classes, models and controllers coloured using purple, green and blue respectively. The library classes include: **org.json.simple.parser.JSONParser***,* **com.google.gson.Gson***,* **org.json.simple.JSONObject***,* **javafx.application.Application***,* **Javafx.scene.control.ListCell**. Within the classes in the diagram unimplemented functions are displayed in coloured text.



![img](https://lh6.googleusercontent.com/LXr1qSbbX9t09tLoe-azjxYeReHFA41CK3ppufvMrjyV3hx-M6CX8YFveCBW8plL-nTq9ovtZVPTn2ng9vJiDYFQ6a9FdoxQ00B_Qp8A5C238bDZCzU40Xy9sgp-2gbM6DbxV15d)



### **SnippetDataManager**

The objective of the **SnippetDataManager** class is to provide communication with the JSON data. All functions of this class operate based on this attribute. Other classes, mainly the GUI controller classes, use this class to obtain display data or to write new snippet data. A controller class can, for instance, parse the user input into a snippet object and pass this object to the *addSnippet* function within **SnippetDataManager** to be converted into a jsonObject and added to *jsonObj.* The controller classes can also request snippet data from the **SnippetDataManager** who will fetch it from the **JSONObject** and return it in the form of a Snippet list to the caller. The implementation of this class is that of a deep model. Complexity is hidden away within the class and only simple functions with descriptive names are made public. **Attributes**

**SnippetDataManager** Is fully composed out of static members. The reasoning behind this is that the attributes of the class are not object bound. Because all functions in the class deal with these attributes, they have to be static as well. The types of these attributes are **JSONObject**, **Gson** and **JSONParser**. The instances of these classes deal with the handling of json data. Parser is used to access functions needed for reading a JSON file or converting a string into a **JSONObject**. The gson attribute is an instance of a class containing methods to convert java class objects into objects of type **JSONObject** and vice versa. The *jsonObj* is an instance of class **JSONObject** and represents the json data read from the json file. This jsonObj is operated on and read by functions throughout the manager.

#### **Operations**

The class has a total of ten operations. Four of these operations are private and thus only used internally. These four are: *initializeJsonObject*, *updateFile*, *jsonObjToSnippet*, *snippetToJsonObj.* The first two handle the reading and writing of a JSONObject. 

*InitializeJsonObject* is used to initialize the *jsonObj* attribute by converting the contents of the JSON file into an **JSONObject** instance, if the file exists. If the file doesn’t exist already the **JSONObject** is initialized by creating a new **JSONObject**. 

*updateFile* is used throughout the class to write the everchanging *jsonObj* onto the file. The *jsonObjToSnippet* and *snippetToJsonObj* functions are solely used to abstract away often used library calls for converting between JSONObject and Snippet objects.

The other six operations are public as well as static. These operations consist of *addSnippet, deleteSnippet, editSnippet, getAllSnippets, getFavouriteSippets, findSnippets*. The function of addSnippet is fairly straightforward, it takes a snippet object, converts it to **JSONObject** instance and adds it to *jsonObj*. *DeleteSnippet* does the opposite: it should take a **Snippet** object, extract relevant attributes and delete the corresponding **jsonObject** from *jsonObj.* 

*GetAllSnippets* returns all snippets from *jsonObj,* whilst *getAllfavouriteSnippets* returns only the snippets whose value for the json key “isFavourite” corresponds to true. The last function is supposed to return all snippets based on a search string. *GetAllSnippets, getAllfavouriteSnippets* and *findSnippets* all return a list of snippet objects. It is possible for these lists to be empty, in case no snippets exist, or no snippets have been favorited/ found.

#### **Associations**

Several associations are linked to this class, this section will discuss three composition relationships the class has as well as the dependency relationship between **Snippet** and **SnippetDataManager**.

The compositions depict that the **SnippetDataManager** class is composed out of the objects *jsonObj, gson,* and *parser.* The reasoning behind this is that these objects live and die with an instance of **SnippetDataManager** since they are created within the class and not passed through functions from external classes. 

The dependency relationship mentioned is due to the fact that the **Snippet** type is either taken as parameter or given as return value. This is the case for all operations except for the *initializeJsonObject* function and the *updateFile* function. No stronger relationship between **Snippet** and **SnippetDataManager** exists because there is no class attribute of the type **Snippet** within **SnippetDataManager.**

There are no stronger relationships (except for the three compositions) between the SnippetDataManager and other classes mainly because all functions provided by the manager class are static. This means that other classes don't have an attribute or local instance of **SnippetDataManager** to still be able to use it.



### Snippet**

The **Snippet** class is an abstraction that used to represent the users code snippets. The class is used extensively by the gui controllers to load user input into and pass it to the **SnippetDataManager.** It is also used by the SnippetDataManager to send back snippet data back in lists to the controllers for display purposes.

#### **Attributes**

The attributes of the **Snippet** class are: *name, description, contentFilePath, programmingLanguage, date, isFavourite* and *tags*. All of which are private to ensure proper data encapsulation. The *name* attribute is of type string and holds, the by the user given, name of the snippet. *Description* is of type String and holds a description of the snippet, *contentFilePath* stores the path to the file where the contents of the snippet were saved.

#### **Operations**

The attributes are initialized by the class constructor, the operation *Snippet*. Not all attribute values are passed to the constructor however. The attributes that are initialized using the constructor parameters are *name, description, programming language* and *tags.* The other attributes are also initialized within the controller but their values are set differently. All operations within the snippet class are public except for one. 

This function is *generateContentFileName*, it's only used within the class and its function is to generate a file name for the snippet based on the snippet name. It ensures that the file of another snippet doesn’t accidently get overwritten, despite it having the same snippet name. The return value of this function is used to initialize the contentFilePath attribute within the constructor. 

Another important function is *writeContentFile* this function is used after editing or adding a snippet to write or rewrite the snippets file. The remaining operations within the **Snippet** class are getters and one setter. The getters are *getName, getDescription, getProgrammingLanguage, getDate, getContent, getIsFavourite* and *getTags*. The getter for the tags list returns a copy of the original attribute to prevent the reference escaping. getters were added to retrieve snippet data for display purposes. The final function is a setter for the isFavourite attribute, this setter will be needed to implement the favoriting functionality.

#### **Associations**

Since this class plays a central role in the system, many classes are dependent on it. This includes all other classes except for **Main.** These associations will be discussed when covering other classes.

#### **Alternative**

Some consideration had been given to modeling an abstract class that would need to be extended by what is currently called **Snippet.** The idea was to name the abstract class snippet and the current **Snippet** class **CodeSnippet**. This would have made the system expandable in the future allowing for different kinds of snippets (notes) that have the common functionalities but also their own attributes and behaviours. This alternative was set aside because the only attribute distinguishing a code snippet from, say, mathSnippet (for storing formulas) is the programmingLanguage attribute. If the system needed to be extended to accommodate different kinds of **Snippet** this could be done by changing the name of this attribute.



### Main**

The Only purpose the **Main** class has is to set the GUI stage and launch the application. These tasks are performed by two functions: the *start* function sets the stage and shows it and the *main* function launches the JavaFx application. The **Main** class *extends* the **javafx.application.Application** class which is needed to create a JavaFx application. The *start* method is an abstract method from the extended **Application** class**.**



### FullSnippetController**

This class functions as a controller for the view that displays an opened snippet in detail. It gets the object of the chosen snippet and displays its information. All the attributes of this class are of type **javafx.scene.control.Label**. The class has two functions *loadSnippetOntoView* and *tagsToString* with as visibility markers package private (default) and private respectively. *TagsToString* abstracts away the code used to turn the tags list into a string for display purposes. It is private and thus used internally by the class, namely in the *loadSnippetOntoView*. The reason for the visibility of *loadSnippetOntoView* has to do with the dependency (discussed later) of **SnippetCellController** on **FullSnippetController***.* The *loadSnippetOntoView* function must be visible to this class, which is in the same package. The purpose of this function is to load the labels with data from the snippet object. The **FullSnippetController** is *dependent* on **Snippet** since the *loadSnippetOntoView* function takes an argument of type **Snippet**.



### SnippetCellController**

This class is used as a controller for the views that are used as the cells of the listview, that sits in the base view. The **SnippetCellController** is important since the user interacts with it to perform some of the systems main functionality.

#### **Attributes**

It has three attributes, these attributes are GUI elements: *nameLbl* and *descriptionLbl* of type **javafx.scene.control.Label**  and *cellBase* of type **javafx.scene.layout.Vbox**.

#### **Operations**

The **SnippetCellController** class contains six functions *updateItem* with visibility protected and *handleViewSnippet, openEditDialog,openDeleteDialog, handleSnippetFavouriting and handleCopySnippetContent which are all private*.

*UpdateItem* is used to either represent a row populated by a list element or an empty row. This function uses the data from the snippet object belonging to its row to set the *nameLbl* and *discriptionLbl*. *HandleViewSnippet* opens a dialog to display the snippet addition form, *openEditDialog* opens a dialog using the view for addition and editing. The difference being that openEditDialog internally will call *loadEditData* from the **AddAndEditController** making sure to load the preexisting snippet data into the the views text fields to be edited.

*openDeleteDialog* is supposed to open a dialog with a warning message, a cancel and apply button. When user presses apply the function should pass the cells snippet to **SnippetDataManagers** *deleteSnippet*

*the handleSnippetFavouriting* sets the *isfavourite* attribute of snippet corresponding to the cell. It then calls *editSnippet* and passes the snippet to the manager to register the change within *jsonObj. HandleCopySnippetContent* is supposed to fetch the contents of the **Snippet** object corresponding to the cell. These contents are then saved onto clipboard to be pasted somewhere else.

#### **Associations**

The class extends **javafx.scene.control.ListCell** from where the function *updateItem* is overriden. *Extending* this class is necessary when creating custom list cells because the listView (declared in **baseController**) only takes objects of type **ListCell** as cells .

Within the *handleViewSnippet* an object of type **FullSnippetController** is created to access the *loadSnippetOntoView* function within this class. This means there is a *dependency* *relationship* from **SnippetCellController** to **fullSnippetController**. Similarly, the *dependency* on **AddAndEditController** exists because *openEditDialog* creates an instance of it to be able to reach *loadEditData.*

There is a *dependency* on **SnippetDataManager** because *openDeleteDialog* will be using it to delete snippets. The final class that SnippetCellController is *dependent* on is **Snippet**. This is mainly because it extends ListCell<Snippet>. The **Snippet** object corresponds to a cell obtained and passed to **SnippetDataManager** to delete or edit/ favourite. 



### AddAndEditController**

Used to control the view the user can use to add or edit a snippet.

#### **Attributes**

Its attributes are all GUI elements. *nameTf, languageTf* and *tagsTf* are all of type **com.jfoenix.controls.JFXTextField**. The other two, *descriptionTa* and *codeTa* are both of type **com.jfoenix.controls.JFXTextArea.** These attributes are used to obtain user input.

#### **Operations**

The class has five operations. Two of which have visibility private because they are only intended for internal use by other functions. These are *parseUserInput* which reads the users input from the text fields and textareas and creates a snippet object based on this information. The function *stringsToTags* is used in conjunction with parseUserInput, its only purpose being converting the string inputted into *tagsTf* into a list that can be passed to the **Snippet** constructor.

The other three operations have a package private (default) visibility marker. The reason for this being that they are called within other classes in the same package (controllers). *HandleSaveNewSnippet* creates a new snippet object and writes the contents of the textArea to its contentfile after which the snippet is passed to **SnippetDataManager** to be added to *jsonObj. LoadEditData* is supposed to prefill the text field using the data from the Snippet object passed to it. *handleSaveEditedSnippets’* purpose is to call the **SnippetDataManager** to edit the snippet within the *jsonObj*.

#### **Associations**

The class is dependent on **SnippetDataManager** and Snippet because functions from these classes are called within **AddAndDeleteController**. In the case of **Snippet** a local object is created as well.





### **AddAndEditController**

This class is the controller for the base view of the system. This controller controls mainly the displaying functionality.

#### **Attributes**

The attributes of the class are *searchTf* of type **javafx.scene.control.TextField,** *sortingCbx* of type **com.jfoenix.controls.JFXComboBox,** *snippetsLv* of type **com.jfoenix.controls.JFXListView** and displaySnippets of type **javafx.collections.ObservableList**. The purpose of the snippet list attribute is to hold the snippets that should be displayed by the listview (*snippetsLv*). The *searchTf* text field is used to obtain String the user wants to search on.

#### **Operations**

The **BaseController** has one public function namely *initialize*. This function is meant to set up initial values for the GUI elements. The *displaySnippet* attribute is also initialized in this function. 

*HandleDisplay* is used to update the *displaySnippet* attribute to hold either all snippets or a subset thereof based on the choices of the user (derived from buttons). Subsets of the snippet collection as well as the entire collection are obtained by calling functions from the **SnippetDataManager** class.

*HandleSearch* gets the user input from the *searchTf* text field and passes the string to *findSnippets* in **SnippetDataManager**. The function updates the listview to hold the search results. This function is called when the search button is pressed.

*openAddDialog* gets executed when the add button is pressed. It opens a new dialog that displays the addition and editing template. The *sortDisplay* function sorts the displaySnippets and updates the listview. It is executed whenever a new option is chosen by the user using the *sortingBox*.

#### **Associations**

**BaseController** is *dependent* on **AddAndEditController** because a local instance of the latter is created in the *openAddDialog* function to get access to the *handleSaveNewSnippet* function in the **AddAndEditController**. It is also *dependent* on **SnippetCellController** since this is the class used to render and display the *displaySnippets* list within the listView. The *dependency* on **SnippetDataManager** is because it is used to obtain snippet lists from. The final *association* this class has is one with **Snippet**. The **BaseController** can have *zero or more* **Snippet** *objects* in the form of the *displaySnippet* attribute. 



### **Class diagram evolution**

The class diagram has gone through many design phases before reaching its current state. Different alternatives have been considered. The names of classes and attributes have been refactored many times.

#### **First draft**

The design process started off with identifying the two main classes of the system. This class was derived as an abstraction of the snippet concept. The second class we came up with had as purpose the managing of these snippet models.

These classes were the **SnippetDataManager** and **Snippet**. The classes that came later were designed around these two. The **SnippetDateManager** only consisted of the three functions we were able to think of at the time.



 *![img](https://lh6.googleusercontent.com/09xMNGufNzJiDlb-JBrPfxKCSEHt-QutYaS0LkliKIwRtC6PoUwJ9jc_LYr28TO4S8vR1iNzCDreQgqgqcCPL8B8h_UDMDbk4sQzfPMMaXu6IqgSuRycC3TwshqvWVu0Np3hh8PB)*

#### **Second draft**

Before we started on the second draft the views of the system were created. This gave us a better idea of what the controller classes (blue) should look like. *Associations* in this draft have been drawn out but not yet in detail. Just enough to portrait the main idea. The operations of the classes did not yet cover all functionality. Also as it stood, at the time, the **SnippetDataManager** class didn't have any static members. This meant that controller classes that wanted to use the **SnippetDataManager** had an attribute of this type.

#### **![img](https://lh5.googleusercontent.com/GGl3HB-75TjgNbRfoBTfgz4pO2fJ9790gK6TcdXwC1TsKajLHO7X4FgrwGjKPya5gm5I7WAO1UbYd9PgA8yCRo2BaQGgcIpIA7qztTF_z3aUXXfrFl2NLZ4IlnqXS5DmkBNXobly)**

#### **Third draft**

During the third phase of the design process a start was made on the implementation. The controller classes had been written and contained references to the gui elements in the form of attributes. The **Snippet** class had been extended with the getters and setters needed by the controllers as well as with the functions needed to write the contents into a file (*writeCodeFile* and *genereateCodeFileName*) these names were later refactored. The *composition associations* of SippetDataManage were drawn the wrong way. Some data types were also displayed in an incorrect fashion. As it stood the library objects were composed out of the SnippetDataManager which is the wrong way around. The implementation was derived from the second draft. This draft was completely rewritten to have no discrepancies with the written code at the time. The SnippetDataManager at this point had all static members. This was done because none of the attributes needed to have an object bound instantiation.

![img](https://lh6.googleusercontent.com/BL-kjZ7SeJ_RuCGbCJUeDFOQALKXeMz3HilVZXRj8h78xCcOZ3tJxZ40FQKnx6Cjf8tbYXZTFbv2oPxUjrLwcFZ4MMr94eG-K6VCBY0Pc3aS-BHTIMWGVsGwTydCv0DBgnIRzRRA)

#### **Fourth draft**

We started thinking about the unimplemented functionalities of the system and how these would fit into our model. No additional classes were added, the unimplemented functions are displayed in colour. The *composition* mistake had been corrected and any changes in the code have been altered within the model as well.

 ![img](https://lh4.googleusercontent.com/Ab3tAwt7Oqq3fDrc4e2gCrIrtqfpI6RPGOGflaYILv6RUMsqVmfelyjI5sCfkx6ESBxSPJf_fjbvuMqEpBNi3x0l44rGhMxBAO-N47AoTOVRd7UkLuvSEllgqMhXbx6S3e19WX-k)

#### **current draft**

Diagram has been rearranged and faulty associations have been removed. Many names have been reformatted within the code; these changes are documented in the model below. 





# ![img](https://lh3.googleusercontent.com/ZMjg2-GnMEdCHcJRUemLqyfywvrdGgs5W73FNtMC2JhtvmaWzYUh0V6NyZbnA5_XzPhoyZXKgYUWi4iWSVLMBpi25XSUNJzShG31SAo4s6gnYdmM6hUmTsF2DMF1HCzNmFf-0kVs)





# Object Diagram 

Author(s): Ørjan Johnsen



![img](https://lh4.googleusercontent.com/_zd7i_lQFEZW6bpCgLdLkurbZhcT0E7-mPatJ8oXbvlHT_8CDlmGYppwA4FsthYa3-xMDiJGRqfMh7upwCwj_QGH8owOWgbDTzZ42q03gS9iCrp1WsDITw8l4w3EOyj1B0n4wJcq)





Object diagrams are an appropriate way to describe how individual instances of classes relate to one another. Different instances of the same class might be related to one another in different ways than how they are visible. Of course, this is very different from a class diagram which covers the relation of the classes in a more overarching way. In fact, the object diagram covers an individual instance of the class, the different fields are filled with example data instead of information about how the data is maintained. Object diagrams are visually very similar to class diagrams and are fundamentally similar due to the fact that object diagrams are a subcategory of class diagrams. 

In this given example of an object diagram, we provide four different examples of a **Snippet** instance. The first two instances to discuss are *bubbleSort* and *quicksort*, they are both assignments and use the same programming language. They are also both tagged with “sorting” and “loop”, which can be used later to derive both algorithms/code. However, they do also differ, in which the *bubbleSort* is tagged with “nested” while *quicksort* was tagged with “recursive”.

Next up are the two image formatting snippets, the first one being a function snippet that changes the format of an image from a png file format to a jpg file format. Unlike the previous two examples, this snippet is programmed in python, and is programmed for a personal project. The different tagging and language makes the snippet appear under different searches. Also, this snippet’s attribute of “isFavourited” is True, which means it will be kept more easily available for the user. The second image formatting snippet is once again programmed in python and formats images into a custom format. However, this function is described as being created for work. This illustrates how two snippets with the same name can both be stored by the user, and the storage of the snippets does not present a problem for the user whatsoever. It has, for the most part, the same tags and it would almost be hard to distinguish it if it wasn't for the description. The tag of “work project” is what will make it different in terms of finding it, and looking at the snippet itself and the description is how the user would actually find this particular one different. 

There is only one **BaseController** and is thus not particularly notable because of its individuality, but it does function as a centre for the available snippets. Of course, in a real scenario the **BaseController** would likely hold a lot more, but the ones here are used for example purposes. Out of concern for usability, every attribute in these diagrams has to be filled which is why there are no instances where they are not represented. Otherwise there would be an example shown of a snippet without descriptions or tags.

Due to the way that the application is structured and implemented, the snippet objects are mainly used as a packaging for the data while transferring it. It is both possible and manageable to implement the application with a stronger use of the class, but our current implementation is simply more efficient. There is one instance that keeps a list of the available snippets where every snippet keeps track of attributes and snippet file path; these are all the direct connections that any given instance of snippet has. 

If we did count the lighter connections that the instances have we would have a diagram about as wide as the class diagram. As mentioned earlier, it is often used as a package format in many instances. Any given instance of the class tends to be short-lived due to this, with an exception for the instances that are kept for the sake of display purposes.

 

# State Machine Diagrams

Authors(s): Elena Ibi

State Machine diagrams are visual diagrammatic representations of the transitions, that objects and other entities in a software system, undergo and of the general dynamic nature of the software system in hand. A state can be thought of as the set of behaviours that an object or an entity can have at a particular time in a software system. A state transition, hence, implies the switch of an object or other entity from one state to another under certain conditions. In UML, state machine diagrams are made up of three main components and these are: 

·    The various states in which an object or an entity can live in at a particular time;

·    The activities, more specifically behaviours, that an object or entity can show within a given state;

·    The various events that trigger the transition from one state to another. 

Generally speaking, the more dynamic the system is, the more states it will have and the more state transitions it will undergo. The way we decided to implement our project track, the Code Snippet Manager, did not allow us to have enough states to model as the entire system was fairly static and hence did not allow us to use the more complex UML constructs related to state machine diagrams. The focus went to implement the features for a local application hence did not allow for much dynamicity. 

State Machine Diagram 1

 The first state machine diagram that we designed shows the behavior of the Snippet Data manager class. In the following state machine diagram, we represent a single entity or object at a time, thus we consider a single snippet at a time rather than a collection of snippets for understandability purposes. The first state machine diagram looks as follows:

![img](https://lh6.googleusercontent.com/_lTbbv1f0TAtLlIN5MxNhpwZVRW0765mj4h_SWRkrKsI9u1lOPxEjAeBgRYmywrGRNlqV2YQa10QwSQO2HWpF4KpeVbicTqnSknUMFyDC57DTLph-QGOFtrVf3RmZVh4o7zQmU_O)

The Snippet Data Manager class is a class that deals with the process behind the creation of the json object. Briefly speaking, the user enters the details of a snippet onto the template and when he/she clicks on the apply button, a snippet object is created. This snippet object is then changed into a Json Object and placed onto a Json array contained in a file. Thus, this state diagram represents some of the concrete behaviors of the Snippet Data Manager class. The state machine diagram above shows an external transition as the transitions are triggered by events occurring outside the two states that are represented. From the initial node, represented as a black circle in the diagram, we get into the ‘not saved’ state which is a state that is entered before a snippet appears on the system. As mentioned above, this diagram shows the behaviour of a single ‘snippet object’ ,in this case, when it first gets created; this implies that, although we might have other previously saved/created snippets in the system which would then place the overall system state within the ‘saved’ state, in this diagram we only depict the state of a single snippet that is to be added onto the system by the user. This representation would allow us to display the behaviour of a single snippet during its creation.

​      The ‘not saved’ state has one entry activity, one do activity and one exit activity as it can be seen. As mentioned above, the ‘not saved’ state is entered when an individual snippet is in the process to be added but data has not yet been collected hence, intuitively, the entry activity is the display of the template on which the user can fill in the data related to the snippet he or she wants to add. Again, there might have been other snippets that have already been saved within the system, however, we do not consider their state in this diagram. While this state has been entered, the user is expected to fill in the fields of the template as shown by the do activity in the diagram. When this has been done, the user can save the data in order to actually create the snippet from the data that has been filled. However, in order to exit this state, there is one condition that needs to be met: all the fields need to be filled. This was a quality requirement that our system specified it would fulfil in order to not create complications later on when searching/sorting the snippets using the tags. Our system does not implement a way to check the type of data that the user enters for every tag/field in order to make sure that it is in the correct format.

​      When the user clicks on the apply button, this event triggers the external transition from the ‘not saved’ state to the ‘saved’ state as can be seen. The entry activity of this state is the creation of the snippet object from all the data that has been collected from the user in the template. As previously mentioned, this snippet object gets turned into a Json object; these were technical and too implementation-based details that we chose to omit from the diagram. All the other features that our system implements, like sorting, searching, setting a snippet as a favourite and so on will apply once the snippet enters this state, however, these will be better shown in the next state machine diagram with the use of composite states.

​      Nonetheless, one of the features we wanted to show explicitly in this state diagram is the editing feature as that would re-trigger a state transition back to the ‘not saved’ state. When the user chooses the snippet to edit and clicks the edit button, the template used previously to add the snippet data pops up. The snippet, hence, enters the ‘not saved’ state again successfully and the user can then modify the required tags which acts as the collection of data (the do activity). When the user clicks apply again (or just adds/saves the snippet from a less UI perspective), the exit condition is again checked to see if all the fields are still filled in after the modification. Once this is done, the external transition takes place and the new (edited) snippet is saved onto the system.

​      Another explicit feature that had to be represented within this state machine diagram, is the delete feature. Once the snippet is deleted, it ceases to exist hence can no longer perform other activities nor any of the other features will apply to it. Therefore, as can be seen in the diagram when the user deletes a specific snippet, this snippet will no longer exist; this is represented by the arrow that goes from the ‘saved’ state to the termination node. 

State Machine Diagram 2

​      The second state machine diagram is a composite state diagram that represents two sub-states that undergo external transition and that both live within a bigger super-state. The bigger super state is the ‘displayed’ state and the two closely related sub-states are ‘sorted by default (date)’ and ‘sorted (any tag)’. The state machine diagram looks as follows:

![img](https://lh4.googleusercontent.com/9MQGN6cJKYJnyuZPK5PPyaLi8jEPn3R0N5dQ7BivRVXVpwStv7qf18Yg3hMBB8AjnjzcaKdrKAyPi_hpOwUQPg0c53AWAG9x2kDKn10RsapSlMo5NwdHjKb56OnO7LVsiBKbBzYo)

 This state machine diagram shows the behavior of the BaseController class as it mainly focuses on the representation of the state transitions that the snippet undergoes when displayed and sorted by tag, all operations that are dealt by the BaseController class along with other classes. The initial state node leads to the first state that can be entered, namely the super-state ‘displayed’. There is one single entry activity, that of adding a snippet, which intuitively will allow the snippet to be displayed on the front page of the code snippet manager, thus enter the ‘displayed’ state. The notion of the representation of a single entity at a time does not apply anymore in this state diagram as all the snippets will be displayed but more importantly because the ‘sorted’ sub-states would not make any sense if we only consider one single snippet at a time. There is a second initial node that will lead to the ‘sorted by default’ state. When a snippet is added onto the system, it is by default sorted by date hence will automatically enter the ‘sorted by default’ state. The user has then the possibility to choose to sort the snippets by using other tags namely name, language or the date (default) tags. When the user clicks on the sort button and chooses which tag to sort the snippets by, this event triggers an external transition from the ‘sorted by default’ state to the ‘sorted’ state where the snippets will be sorted according to the tag chosen by the user.

Our system allows the user to sort the snippets by the default tag also once another sorting tag has been used. This means that for instance if the user chooses to sort the snippets by name, he or she can later choose to sort those snippets again by date as date will also be provided in the list of choice of sorting tags. However, the state machine diagram above does not show an arrow that goes back from the ‘sorted (any tag)’ state to the ‘sorted by default ‘state to imply the choice of date as a sorting tag. This is simply because choosing date as a sorting tag is the same as choosing any other tag. The diagram shows the default state just to imply that it is the state that the system will be in at the beginning if no sorting state transitions have been triggered yet, or more precisely if no sorting operation has been performed yet. The exit operation is the deletion of a snippet or more snippets as they will not be displayed anymore.; whether it is a single snippet or multiple snippets is not an issue here again as the state representations apply to both the scenarios. The last component of this state diagram is the final state node which shows the end of the sequence of states. 



# Sequence Diagrams

Author(s): Wassim Mayyasi

**User Adds Snippet:**![img](https://lh3.googleusercontent.com/E114jkhUr7jB1wxrxm_tZnX4YZD1njxv6plmDgB9bUl2p6Dr7E-pBvAzbBJ4vCYnOoBxUycHQA3UpQUZxy0E88GCQDXfKElKGQ2qnKtXxBbCopB-U2SwgKj4-fD9ZAVRKjvYNu1K)

Adding a snippet is a fundamental feature of the code snippet manager, and it is the only feature (other than parts of displays) we have implemented so far. This action will be done on the created Graphical User Interface (GUI) implemented using **JavaFX** and **JFoenix**. We have implemented some display to be able to see whether the add implementation is working properly. The shown sequence diagram has the series of function calls (events) that happen when the add action is initially provoked or started. Some prescriptive functions are shown, but the functions that we have not implemented and are just used from an imported library are not shown. Some of these functions include the GUI functions that set the dialog, also the function that parses the snippet type to a json type, and the add function that adds that json object to our stored json array.  

This sequence diagram starts with the user’s action, the user interacts with the base user interface controller (**BaseController**). The BaseController as mentioned above, is partially implemented, as we are still in the implementation stage, just to give us some feedback for the add function whether it’s working. The user initially starts by clicking on the “Add” button, and this clicking event in turn calls the function openAddDialog() from the **BaseController**. The function openAddDialog() first displays a dialog in which the user can fill in the information for the snippet, such as name, description, and so on. This dialog display is considered a receive event to the sent event, the function openAddDialog(). Nothing happens on the back-end while these inputs are made. Here, we have an optional fragment, for the Apply button to be pressed, the rest of the sequence diagram depends on this condition. If the user clicks the Apply button, an instance of the **AddAndEditController** class is loaded and then with that instance a function handleSaveNewSnippet() is called. This function parses all the fields the user filled in(using the function parseUserInput()), to a **Snippet** instance. Then it writes the code (using the function writeCodeFile()) that the user input into the code text field to a text file that is stored in a folder “snippet_code_files”. Then finally, the **AddAndEditController** interacts with the **SnippetDataManager** to truly get the snippet added to the system. This is done by calling the function addSnippet() with the **Snippet** instance earlier created passed as a parameter. The addSnippet() function is responsible for parsing all the attributes of the snippet added to JSON objects and adding them(using .add() from JsonPath) to our JSON file in store “snippets.json”, and then update that JSON file with the function updateFile(). Finally, the last step happens back in handleSaveNewSnippet() in **BaseController**, and that is to update the list of snippets with the addition of the new one for the user interface. The function handleDisplay() goes on to calling getAllSnippets() from the **SnippetDataManager**, which has the updated snippet list. 

There is a quality requirement that falls under these set of events, which was not shown in this sequence diagram, because it has not been implemented yet, but will be. When the user fills in the dialog of information for the snippet, if they miss or they don’t fill up one of the attributes, and try to Apply the addition, they will be notified to enter all attributes. They can not continue without this constraint being fulfilled.

Alternative:

We were bouncing ideas around whether we want to store the added snippets as json objects or just keep them as **Snippet** instances. The thing with using something other than json objects is that we would have needed to reinvent the wheel by creating a function that would add these snippet instances to a file, while json libraries already had functions that fulfil this purpose. This alternative implementation also proved not useful for all the other features we have implemented, where we would have needed to create functions that fetch, find, delete and so on, where these functions are already in existence for json objects. So, using JSON objects from our point of view seemed the most efficient.



**User Deletes Snippet:**

Also as part of the code snippet manager, we feel the delete feature is a requirement for the user of such an application. There must be an opposing function to the add function, so when the user wants to revert his action, for whatever reason it could be, it is made possible. This opposing function would be the delete. The delete feature, although not yet implemented, is planned to have a very simple and straight-forward implementation. The way our system stores the snippets is quite simple with the usage of the json file and the text files with the snippets in them, this helps us easily extract and delete the chosen snippet from the file. Over this whole sequence diagram, the interaction partners are the **SnippetCellController**, **SnippetDataManager**, and **BaseController**, however, that’s with disregarding the user as an interaction partner that actually initiates this entire series of events.![img](https://lh6.googleusercontent.com/ZbmeQAvaBKLfWlTWWYkoWWrbuz90RL3tsCs4YSCHFPtYB1m9PjlzTViBQ6oKymWRRAXp-fgw713m3UUc4DTPH10XS9XB1uhgHsF2Pu5z6_QaohzbNpNBWLn9DtOQXKCiw1cpgE6G)

As just mentioned, and the previous sequence diagram as well, this sequence also starts with an action taken by the user, and here the series of events start with the user clicking the delete button at the snippet they would like to cut out. This action provokes the function openDeleteDialog() found in the class **SnippetCellController**. **SnippetCellController** is used to group all the snippets in store, and with this grouping, any buttons can be added to all snippets in display, this is used for several features implemented including the delete. This interaction will prompt (as a receive event, or a response message) the user to reassure them that they intended this action to be done. This prompt is created just to double check in case of misclicks, so that no snippets are lost in that case. And as seen, the rest of the sequence diagram depends on this optional fragment, that the user assures the delete. After the user reassures and clicks OK, this calls the function deleteSnippet() which is part of the **SnippetDataManager** class. Our **SnippetDataManager** deals with the json objects, so this interaction is necessary in order to fetch the chosen snippet, and fetch the text file to the respective snippet. The name deleteSnippet is very general, we do not feel it is necessary to be more prescriptive with function names. What actually goes on in this function is that it first uses the snippet instance passed as an argument to find the snippet from the json file using the name or date. Then, the file path of the snippet is also found from the instance and the appropriate text file is found this way, and both the json object and the text file that stores the code are deleted (with **JsonPath** .delete() and **java.io.File** .delete() respectively.). Finally, after the deletion of the snippet from the system is completed, the instance of the json file needs to be updated and this is done with updateFile(). After all that, what’s left is the update of the display which happens similarly to the add function, except here, we would have an instance of the **BaseController** from the **SnippetCellController**. The **BaseController** controls the display of the list of snippets, and so that’s why the control is passed on to it with handleDisplay(), and it gets the list of snippets from **SnippetDataManager** with getAllSnippets(). Then it goes on to display the list of snippets without the deleted snippet.



Alternatives:

  At some point, we saw potential in keeping in store all the deleted files instead of actually deleting them. So, whenever the user wants to delete a snippet, we would store this snippet in a sort of internal “recycle bin”. This would’ve been helpful in case of a user regretting the deletion of a snippet and wants it back. However, this felt a little inefficient in the long run. For the case of it being helpful when the user has regrets, we decided to implement the reassurance message that gets displayed to have the user be sure of their actions and that it is irreversible.

  Another very small alternative was regarding the **SnippetCellController**. At some point, we wanted to include all the buttons at the top of the front page (in **BaseController**), and the user would select the snippet and then click on the button corresponding to the action they want to take like “Delete” or “Edit”. However, this was not chosen because it made the user interface inefficient, while having the button for each snippet showed a better interface where they just clicked 1 button instead.



**User Edits Snippet****:**![img](https://lh5.googleusercontent.com/ubqWbK9oby1FjuWJk9o8eSUizL2V-m-3p0jniFGE5ohZXwAIuliqd4IkuWRyhagtbODMqpxGRaMfMJHU8aE49abGEYKUTmj76GqUGuYHqW-FrJApYDYtxrm5-Mr9_S5YwqSijhEi)

Another very important action to exist in a code snippet manager that adds comfort and ease to the use of the application is editing of snippets. This feature would allow the user to quickly update their old code snippet, or in case of errors in the description or tags, fix the attributes of a snippet. In the display of the list of snippets, there will be a button for every snippet (next to the delete button) that is used for editing. There are steps throughout the implementation of this feature that are added just to allow the system to follow some quality requirements for usability and reliability. An example to that is the reassurance dialog, or in case of empty text fields, the user will get notified by means of a dialog as well. The interaction partners in this sequence diagram as shown are, **SnippetCellController**, **AddAndEditController**, **SnippetDataManager**, **Snippet**, and **BaseController**. 

  To begin with the editing action, the user starts by clicking the edit button of the desired snippet. This click calls the function openEditDialog() from the class **SnippetCellController**. Inside the function, it starts by creating a dialog similar to the one that was used to add the snippet. Next, loading the data of the snippet is needed, this is done by calling the function loadEditData() with the parameter of the snippet. The load function, which is in the **AddAndEditController** class, sets all the text fields of the dialog to their respective value from the snippet that was passed as argument. Then the user is shown the dialog with the set value of the snippet and the dialog is editable. After doing whatever they want, the user clicks Save, similar to what was mentioned at the add sequence diagram, the feedback in case of empty text fields will also be implemented here. This sequence will not proceed unless all text fields are filled in, and as shown in the sequence diagram in the optional fragment, “all text fields filled”(which is not specific because the exact condition used isn’t specified by us yet) is the condition to the continuation. If true, the function handleSaveEditedSnippet() from the **AddAndEditController** will be provoked. In this function, it first prompts the user with a dialog for reassurance that they want to edit, this is just a step to make the application more reliable for the user. And this is another optional fragment, that will not carry on until the user presses OK. After the user confirms the reassurance message by clicking OK, the function writeContentFile() of the **Snippet** class, will be called first, to update the code in the text file by overwriting it. Then the function editSnippet() will be called to the **SnippetDataManager** class. The purpose of **SnippetDataManager** in this situation is that it holds all of the snippets as JSON objects, so it would translate the chosen snippet back to a JSON object. The editSnippet() function then handles the editing in detail, by first re-assigning the attributes of the given snippet to their respective new values. The only thing left is to finalize this process, is to use updateFile() which updates the json file. Now that the editing is complete, a similar action to the one done in adding and deleting, which handles the display, is left for the user to see the updates in case of name or description change. As shown in the other sequence diagrams, we will have the function handleDisplay() be called from the **SnippetCellController**’s handleSaveEditedSnippet() with an instance of **BaseController**, which in turn will getAllSnippets() and that will update the display.



**User Favourites Snippet:**![img](https://lh4.googleusercontent.com/sVNlw7FLg-UcVxSaJn6Ozi2Hqj9fDF-NKYgqA7SDSNoJLkW7NJrv404ElB44KkbFKZQTQkB2jjUGxReXdcz_bJHAD7QID2a5gNAMvL6SBt73_CXVZxfWkX89_cpY4gAfQwoiszc0)

When deciding to create the favouriting feature, we knew it was not necessary, it was just an additional functionality for our system to have. The interaction in these sequence of events carry on between the user, **SnippetCellController**, **Snippet**, and **SnippetDataManager**. For each snippet, there will be a button that can be used for favouriting. Upon pressing this button for one of the snippets, the series of events described in the sequence diagram will hold. This action has no effects on the display which is why there are no interactions happening with the **BaseController**. Also, this means that the events occurring in this sequence are all back-end functions, in which this would make it harder to tell whether the action succeeded, until of course the list of favourites is checked. 

  At start, the user clicks on the button designated for the favouriting of snippets. This action fires the function handleSnippetFavouriting() from the class **SnippetCellController**. As previously mentioned in earlier sections, **SnippetCellController** is responsible for all the snippets and having each individual button have its designated button, in this case it’s the favourite button and the handleSnippetFavouriting() function call. The plan for the setting of the favourite is chosen to be very intuitive. First, we get the current assigned value of the attribute “isFavourite” of our selected snippet, using getIsFavourite(), this call goes to the **Snippet**. Then all what’s done is we negate that returned value into the parameter of the set function as so: setIsFavourited(!getIsFavourite()), both these functions are called with the **Snippet** instance. This Snippet instance can be retrieved using this.getItem() from the **ListCell** class. After this full interaction happens between **Snippet** and **SnippetCellController**, the “isFavourite” is now set as the new value either True->False or False->True, but in our case the action of this sequence diagram is favouriting a snippet, so it is False->True. Next, the snippet has been edited, however, the JSON file needs this update to happen. So the **SnippetCellController** takes this newly favourited snippet, and passes it to **SnippetDataManager** with the editSnippet() function. Here, on the back-end the favourite is finalized by editing and updating the json object and then the JSON file respectively. The update is done with the updateFile() and this ends the full sequence of events.





# Implementation

Author(s): Nour Oujjit

Location main class: src/main/java/controllers/Main

Location Jar file: software-design-vu/out/artifacts/software_design_vu_2020_jar/software-design-vu-2020.jar

video: https://www.youtube.com/watch?v=crMJ13V-V-k



## From UML models to code

After the initial idea was modelled the implementation process started. Based on the class diagrams we formulated the object and state diagrams. These diagrams together gave us better insight in the sequence of actions that need to be taken for the user to perform a particular task. This enabled us to draft the sequence diagrams. After we had the initial idea on the structure of the system the implementation process began. The classes described in the class diagrams were written. When things didn't work out as planned in the models whilst writing the code, the code was refactored and the diagrams were adjusted to model the new situation. After which the models were expanded using the new insight on the system. When the code was finalized we made sure to work away any discrepancies from the models.



## Implementation of models

#### The SnippetDataManager class

We store the snippet data of the user within a JSON file. The snippets are represented within this file as JSON objects. The key-value pairs in each of these objects represent the attributes and values of the corresponding Snippet object. These JSON objects are nested within a JSON array with key “snippets”.

In order to read and write from this file we utilized classes from org.json.simple and java.io. The classes from java.io provided us with the functions to open files and functions to write onto files. In combination with the JSONParser class from org.json.simple the JSON file is read in and parsed into a format that could be read from and altered throughout the duration of the program. This format is a JSONObject object from the JSON simple library mentioned before.

All attributes of the SnippetDataManager class are static. When the program starts execution these attributes are all initialized within a static code block. The jsonObj attribute is initialized using data parsed from the snippets.json file. If this file does not exist it is initialized by making a new JSONObject. A key-value pair (“snippets”, new JSONArray()) is then put in the empty JSONObject. This is done to ensure the json data gets formatted as discussed before in this section.

After the jsonObj attribute is initialized only functions within the SnippetDataManager class can operate on it ensuring proper encapsulation of the data member. After which the object can be written back onto the file (updateFile()) with the help of aforementioned java.io library functions. 

Any changes made to the metadata of snippets will be done within this class. This includes addition, deletion and editing. Data retrieval will also be handled by this class. All these functionalities can be realized through the usage of the com.jayway.jsonpath library. This library provides us with static functions that can be used to add, delete, set and read JSON data. All that is needed to be passed to them is a JSONObject and a jsonPath. Retrieving all snippets in path $.snippets (so all JSON objects within the array whose key is “snippets”):

ArrayList<JSONObject> allSnippetsJson = JsonPath.*read*(*jsonObj*, “**$.snippets”**)

Because “$.snippets” is the main path, where all snippets can be found, a constant has been declared for it, namely SNIPPETS_PATH. All that needs to be done after, for instance, retrieval is converting, in this case, the ArrayList of JSONObjects into an ArrayList of Snippet objects which can be returned to the caller. Converting between these two types is done by functions provided by the gson attribute of type com.google.gson.Gson. Since functions that will be added in the future will also use the this library to convert between JSONObject and Snippet, the library calls are abstracted away into two functions: jsonObjToSnippet(JSONObject jsonObj) and snippetToJsonObj(Snippet snippet). This helps keep the code clear and easily maintainable. 

classes outside of this module do not need to understand the inner workings of the snippet manager. All that is needed to be known is that to add a new snippet, the addSnippet function needs to be called and a snippet object needs to be passed to it. Other public functions are designed following the same principle. When operations need to happen in a particular order, external classes do not need to be aware of this. This is because ordered operations are grouped together within the public functions. Below is an example of the addSnippet function.



**public static void** addSnippet(Snippet snippet) **throws** ParseException {

  JsonPath.*parse*(*jsonObj*).add(**SNIPPETS_PATH**, *snippetToJsonObj*(snippet));

  *updateFile*();

}



As the example shows an external class that wants to add a snippet does not have to concern itself with also updating the file. The updateFile() function is private to the SnippetDataManager class. The design of the SnippetDataManager follows the deep model principle. It effectively hides complex implementation inside the class. 



#### The Snippet class

The snippet class is a data model that abstracts away the concept of a snippet. Aside from the attributes and getter and setter functions the class provides three more functions. 

The constructor provided by the class only takes four parameters. These parameters are used to initialize the name, description, programmingLanguage and tags attributes. The isFavourite attribute is initialized to false, cause when a snippet is first created it is not yet favourited. The date attribute is initialized using the LocalDateTime.now() function. This causes the date attribute to save the time of creation. The final attribute that is initialized using the private function generateContentFileName(). 

generateContentFileName generates a unique filename within the specified folder. The name is generated using the snippet objects name attribute. This function takes special care to avoid overwriting the file of a snippet that has the same name. It does this by means of a while loop. While there exists a file with the constructed file name, it will increment a count variable and add it to the name. When the while key evaluates to false the filePath gets returned.

The other two functions worth mentioning in this class are getContent() and writeContenFile(String codeString). As the name of the latter suggests, a snippet object can write a string of code onto a file using this function. The path of this file is specified in the contentFilePath attribute of the object. If the folder used for storing the content files does not exist this folder is created before the passed string is written to the file.

getContent() is used to retrieve the text stored in the snippet content files. A snippet object can use this function to read and return the code from its content file.



### Implementation of Controllers

Most of the classes are controllers. The main purpose of these controllers is to either send snippet(s) to the SnippetDataManager or to display snippet(s) retrieved from the SnippetDataManager. 

#### The BaseController class

The base controller controls the gui elements specified in the BaseView.fxml file. At initialization the controller sets the cells of the listview. It does this by first fetching the list of all snippets from the SnippetDataManager. This list is then cast to an observableList. A Listener is added to the observable list. When this listener detects changes in the lists contents it will reset the cells of the list view. This way whenever a new snippet is added the list will be reset and thus the table updated. After initializing the list and adding the listener the cellFactory of the listview is set using snippetsLV.setCellFactory(s -> new SnippetCellController()). After which the observable list is added to the listviews items. The setCellFactory method enabled the loading of custom cells the controller of which is SnippetCellController.

The openAddDialog function is executed when the user presses the add button on the view. This function opens a dialog and loads the AddAndEditController.fxml file into it. When the user presses the apply button of the dialog the instance for the AddAndEditController class is retrieved from the Fxml Loader. This object of AddAndEditController is needed to reach the handleSaveNewSnippet() function. This function collects the user input from the AddAndEdit view, creates a snippet object and passes this object to the addSnippet function in SnippetDataManager for it to be added. An alternative to this would be to define the base controller as controller for the AddAndEditView. But this would entail that the fxml attributes of two different views would be handled within one controller which would make the code more complex and hard to maintain.

#### The SnippetCellController class

The SnippetCellController, mentioned before, is used to load a SnippetCellView.fxml file onto the cells of the listview of within the base view. It does this by overriding the updateItem function from its parent class ListCell<T>. SnippetCellView labels that display the name and description of the cell's snippet as well as the.

The handleViewSnippet function is triggered by the onaction event of the view button. Like the openAddDialog function of the class discussed before it opens a dialog and loads the FullSnippetView.fxml file onto it. It then retrieves an object of the fullSnippetController to access the loadSippetOntoView function. To this function the cells snippet is passed. loadSippetOntoView uses the getter and setter methods of the snippet object to load the labels of the FullSnippetView and effectively display the most important info of the snippet



### Implementation of Views

The FXML files for all views have been generated using the scene builder tool. The controller of each fxml file has been added using: fx:controller="controllers.BaseController" in the root pane of the file. The gui elements are linked to their controllers by using their fx ids.