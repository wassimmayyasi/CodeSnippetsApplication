Assignment 3

## Summary of changes of Assignment 1

Author(s): Elena Ibi

### Introduction and Features Section



- We made sure that the GUI changes that our latest version of the system has seen were reflected in the assignment 1 documentation. Some buttons have changed position in the display from how we initially promised we would have them; for instance, copy and edit are now placed at the bottom of the template and favorite, view and delete buttons are now next to every snippet on the front-page;
- The reason for including the copy button has changed. Previously it was to fasten the process of copying the snippet body without letting the user open the template, however, now it is to just let the user copy the code fast without having to do it manually. This was partially as a result of our decision to place the copy button at the bottom of the template (which hence requires the user to open the snippet) instead of placing this button next to every snippet on the main display page. 
- We added the saving of a snippet on the ‘Adding’ feature as suggested;

## Summary of changes of Assignment 2

Author(s): Elena Ibi, Wassim Mayyasi

### General:

- We tried to pair up for most of the sections of assignment 3 instead of individually dealing with them as suggested however, this was difficult to achieve in some cases since one of our team member had issues with the IDE and the fourth team member had to leave the country and stopped taking this course which left 3 team members in total. Regardless of that, we tried to give suggestions and input and checked each other’s work;

### Feedback for Object Diagrams:

- We explained in the text that the snippet instances shown do not show the code because the code is not an attribute of the class. We further elaborated on the fact that instead of the actual code as an attribute, we provide a path to where the code is stored;
- We explained in text that the Json file is only used to store the snippets that are added onto the system by the user hence they are updated when the user adds a snippet. For this reason they are not relevant to be shown in the object diagram. Furthermore we provide a detailed description of the JSON file and of its structure as suggested;
- We provide in text, how the two snippet instances provided came to be and we show their corresponding controllers.

### Feedback for State Machine Diagrams:

- We made sure that all of the three State Machine Diagrams corresponded and displayed the internal behavior of one class only;
- The previous exit activity ‘Check if all fields have been filled’ has now become the guard condition for the event triggering the transition in the State Machine Diagram 1; we also added other guard conditions whenever they applied in the other state diagrams;
- We limited our ‘do’ activities, as suggested since they are not all necessarily executed while in the state, to just the copying and editing feature of the snippet. We chose to display the copying and editing activities of the snippet because they apply in almost all the states shown in the various diagrams and additionally they are two of the features that have not been extensively analyzed in the diagram; this change was also mentioned in text in the corresponding sections;
- We clearly stated, in state machine diagram 1, that deleting a snippet means that the snippet will no longer exist hence will no longer be displayed either;
- We dedicated an entire state machine diagram (diagram 3) for the snippets marked as favourite by the user to show that there is an extra display specifically for these snippets; 
- We made sure that all the events corresponded to actual operation/function calls, shown also in the class diagrams.

### Feedback for Sequence Diagrams:

- We made sure no weird gaps or texts are randomly written, several proof reads.
- We created a variable “isFavourite” that takes the return value for the getIsFavourite(), then passes the !isFavourite to the setter function. Also, we used the ‘!’ notation as suggested, and with all that, we feel like it is very clear and matching to the implementation closely.
- We fixed all the guards to be more code specific.
- For the feedback on using “reassurance message”, we had no implemented operations that do that, we use an imported library that asks the user for a confirmation. We wanted to show this step, even though we haven’t implemented the alert, because it would add to the flow of the sequence of events making it more sensible to what is happening in the end. Also, keeping it abiding to the way it was implemented in the code.

Feedback for Implementation:

- This is not really a change, but addressing the feedback we got last time, with regards to input checking. We had/have a quality requirement “Empty Fields” which we have implemented now, but back in the previous report it wasn’t. So, now it does not allow empty fields for either adding or editing.
- The comment we got with regards to the file naming, causing issues with weird characters like “??” and so got us to change the way we generate file names. More on that in the implementation section.

## [Application design patterns](https://drive.google.com/file/d/11ZYfYYXKNdSaMGojpPGUXDGSk6vL9nA5/view?usp=sharing)

Authors: wassim Mayyassi, Nour Oujjit

![img](https://lh4.googleusercontent.com/x_TdF1eXjNvj03JtyqEysFEjDb73cTSQxRRpbKS5hKJ04Ue_dbZtvJXAukQjZ-oihZQlh8-dN4U1-zHEH0iQuIq2VMX1JxwV3ItOFPN54OspyNCJiZM4EM4aRoQxSNsaz-62W3JG)





| ID             | DP1                                                          |
| -------------- | ------------------------------------------------------------ |
| Design Pattern | Singleton pattern![img](https://lh5.googleusercontent.com/3uD0wsKl5o_YNty0-7ZePGirM3VMymwFNywS0G8KTxXqIdH_PnWtxz0laMzcBIq_b55OYGVytMoeWLkrH7ArS9dz3QKP7y88nE0Vohm86av5fksL7J_YRphBkGeVILTsmGHBg5jI) |
| Problem        | The Snippet manager class contains the most important data collection of the system. Namely, the JSONObject that holds all the snippet data. At the instantiation of this class the *jsonObj* attribute is initialized by reading the json data from a json file and parsing it into the JSONObject format (after instantiation the json file is not read again). Throughout the system's runtime, the data stored in this class is manipulated by several controller classes that communicate with the user directly, by using the gui. Having each of these classes instantiate their own SnippetManager object would cause several problems. The first one being that all the classes need access to exactly the same data. Having this class be instantiated multiple times would cause the data to be decentralized and different parts of the program to have access to an incomplete snippet collection that are incompatible with one another. As an example let us take two controller classes, controller1 and controller2, each one of these controllers instantiates their own **SnippetManager** object. Let’s consider the scenario in which the constructor for controller1 is called first, controller1 would then instantiate its **SnippetManager** object. When the controller of SnippetManager is called its *jsonObj* attribute is initialized by parsing the snippet.json file after which the constructor for controller2 is called instantiating SnippetManager in the same way.The SnippetManager Objects in controller1 and controller2 now contain exactly the same data. The **SnippetManager** allows external classes to manipulate the data by providing them with public functions that enable these classes to add, delete, favourite and edit snippet data stored in the *jsonObj* attribute, after everyone of these manipulations the change is registered by writing the JSONObject onto the file. The problem occurs when controller1 triggers one of these manipulations within its SnippetData object. Controller1 for example deletes a snippet. The snippet entry in *jsonObj* of its **SnippetManager** instance is deleted, as well as the code file belonging to it. Controller2 is completely unaware of this change. The snippet entry deleted by controller1 is still present in the jsonObj attribute of its SnippetManager instance but the file corresponding to its path has been deleted. When controller2 tries to display the code file of this snippet the system will crash. Aside from this problem there is no need for custom instantiation that would give back an object containing different attribute values when calling the constructor. This means that instantiation of the class can just as well be done by the class itself. |
| Solution       | This pattern enables the prevention of instantiations of multiple SnippetManager objects effectively keeping different classes from accessing data that is incomplete and unidentical. As the pattern prescribes the constructor is to be made private, which disables outside classes to create their own instance. Instead a static function has to be provided for these classes, for them to reference the singleton object stored within **SnippetManager**. This ensures that all classes access the same data. |
| Intended use   | The singleton instance will be stored as a static constant. It will be initialized by a static initializer and thus the instance of **SnippetManager** will be created as soon as the class loads. The classes that need to operate on the json data stored in SnippetManager are the controller classes. These classes can do this by having a reference to the instance stored within **SnippetManager**. Let’s say a controller wants to add a snippet, the instance can be obtained using: SnippetManager.instance(). The returned reference can either be assigned to an attribute within the class (when multiple functions use it), in which case the reference is obtained when the controller's constructor is called, or operations can directly be called on it: SnippetManager.instance(). addSnippet(snippet). |
| Constraints    | This pattern imposed no constraints on our overall design.   |



| ID                 | DP2                                                          |
| ------------------ | ------------------------------------------------------------ |
| Design Pattern     | Observer pattern![img](https://lh4.googleusercontent.com/i3J3UZtYyglQ7N3sR33BQ00yEJiaI7fDOypcCQYJK358h_oiHKPYX-ZNnZzpz-5cMre8Dh4rUGb11RMohXyLV-KPt7zQwy3ACHHltTSvAX4Ceb-c4r49Bb7sAFI-0Q4jZ0zFbuFh) |
| Problem            | Constant changes occur in the data being displayed. Snippets are added, deleted, favourited and edited by the user but the displayed data doesn’t change immediately to indicate that a change has taken place. The displayed data would only reload when the system is turned off and on again or when the “all” and “favourites” buttons are clicked, which reload the data in the listView. After the user adds a new snippet this snippet should immediately appear in the listview of the base manager. The same goes for when a user deletes a snippet. After addition the new snippet is not visible within the listView that displays the snippets. Same problem holds for editing and favouriting. When a snippet is edited the fullSnippetView should be refreshed with the edited snippet and when the listView is displaying the favourite snippets it should be reloaded whenever a snippet is unfavorited (so it no longer displays that snippet as a favourite). |
| Solution           | The idea of this pattern is to have external classes observe changes in data stored within another class [1]. This patterns solves our problem because, it provides a way to notify the **BaseController** (which holds the listview) and the **FullSnippetController** (which displays a snippet in detail) that a change in the json data has occurred and that the gui elements responsible for displaying this data have to be reloaded to represent this change. This would be done by having the **BaseController** and the **FullSnippetController** implement an interface that enforces the implementation of a call back function. In our case the call back function is *contentsChanged()*. This call back function takes no arguments and thus the push strategy will not be used. Instead the pull strategy is chosen which allows the observers to pull data from the model/ subject (slight deviation from this concept see additional remarks). The observers both have their own implementation of this callback function. When the callback function in **BaseController** is called the listView is refreshed by fetching data ,in the form of a snippet list, (in accordance with its current state see DP3) from the **SnippetSupplier** class. On the other hand, when the callback function in **FullSnippetController** is called the labels are reloaded with the updated data of the snippet it was displaying. |
| Intended use       | This pattern, in our systems case, involves the following three classes: **SnippetManager, BaseController** and **FullSnippetController**. In our implementation of the pattern the SnippetManager class contains the data that needs to be observed, and thus represents the subject/model class. **BaseController** and **FullSnippetController** implement the **SnippetDataObserver** interface which makes them the observers.When the system starts an instance of **SnippetManager** is created. This instance contains a list of **SnippetDataOberver** objects. A call to the notification method must be inserted in every state changing method [1]. Whenever the contents of the *jsonObj* in **SnippetManager** are altered in any way, all **SnippetDataObservers** should be notified of this change. SnippetManager allows external classes to perform these changes by providing the following public functions: *addSnippet*, *editSnippet*, *toggleFavouriteSnippet*, *deleteSnippet*. These functions should perform the change and in the end notify the observer classes. This would be done by calling a function (*notifyObservers*) in **SnippetManager** that loops through the list with **SnippetDataObservers** and calls the callback function (*contentsChanged*) on each of them.Whenever an instance of **BaseController** or **FullSnippetController** is instantiated it will add itself to the list of observers in **SnippetManager** by calling the *addObserver* function within it: snippetManager.addObserver(this). A new instance of a controller is created every time the view (fxml file) corresponding to it is loaded.The **BaseController** is only instantiated once and thus doesn’t need to be removed from this list at any time during the running of the program. On the other hand the view for **FullSnippetController** can be loaded multiple times by the program. This is because the screen that loads the view can be opened multiple times by the user. In order to prevent the **SnippetDataObserver** list from filling up with unused instances of **FullSnippetController** a way has to be provided for it to remove itself from the list. This can be done by calling the *removeObserver* method in **SnippetManager.** Whenever the dialog on which the FullSnippetView is loaded is closed the controller instance, created during the loading process, is removed. |
| Constraints        | Inorder to implement this pattern three additional functions had to be added to the **SnippetDataManager** *notifyObservers*, *addObservers* and *removeObservers*. An interface had to be created to represent the Observers. BaseController and FullSnippetController had to implement it. |
| Additional remarks | Slight deviation in structure of the pattern was caused by a split of the SnippetManager which was suggested in the feedback of assignment two. The **SnippetManager** contains the functions that operate on the “observed” data but not the functions used to retrieve it. These functions are provided by **SnippetSupplier.** The strategy that fits our design is the pull data flow strategy. To implement the dataflow strategy, observers must have a reference to the model/subject [1]. However in our case, instead of “pulling” this data directly from the subject class (**SnippetManager**) the notified observers pull it (indirectly) from **SnippetSupplier** instead. However the observers do not have an instance of SnippetSupplier either. The data is reloaded indirectly through the DisplayState classes which will be discussed in DP3. |



| ID                 | DP3                                                          |
| ------------------ | ------------------------------------------------------------ |
| Design Pattern     | State pattern![img](https://lh4.googleusercontent.com/GH90Yq2sPCKPrDI6nlszfu0Sdg1Qw39VBEhYZJiOW4Mjh9vrlcv-lLmEetarhqoc-OCVSedAzvzMfZifq23QGX4x2X1G0IZbpS5URMxRLRe-3UBw9kMNWGkzTllz7blEN_B5fuRy) |
| Problem            | The listView within **BaseController** should be filled with the collection of snippets that represents the interactions the user had with the gui. Let's say the user presses the favourites button to display the favourites snippet. The user then starts typing in the search bar. The system has no way of remembering what collection of snippets to get the search results of. So, although the user selected favourites, the listview loads the search data over all snippets. Another problem occurs when a new snippet is added. After addition the listView is reloaded (using observer pattern DP2). Even though the user had selected to display favourites before, the addition of the new snippet triggers the listView to load all data. This is because the system doesn’t remember the state that it was in. We have managed to solve this issue by using a switch statement within the *loadDisplayData* function (responsable for reloading the listview). And tried to represent the users actions by using enums but this led to a bulky switch statement. As well as weird conditional statements in the function responsible for providing search results. However this solution is bad design. The code is unclear and many changes are needed to add a new snippet selection option. This solution is difficult to maintain. Example of the problematic switch case:![img](https://lh5.googleusercontent.com/VJxaMpMGOGINbHuPqlj4xPv7TM90vfSuADxdSJncjy4EYw0OUEDao2uvD-Ng7orH_qcXjGQKtJPT4q5f3SLrY5gIcHiemdmxNOhuQUV9omowv3kMnlWfdKzucuhzK3c2AhJkCk3T)Example of the problematic conditional statements: ![img](https://lh6.googleusercontent.com/Oc3rWhAhlO9cXAks5hzcbA_MII2uI3iJ7P17QLNsjuKwaxfdqrLqqj1EUc1fuPUuH-zuydqZZeTjc3CeA3sFr5au-l4jD441XA57fnhUW_NvmMjMLvF1qyCMaq2xUaQPFK1ICuUm) |
| Solution           | The state pattern allows an object to alter its behaviour when its internal state changes [2]. In our case the internal changes are caused by the user interacting with the gui.![img](https://lh6.googleusercontent.com/n8K5-q5Uu7KSrcc8cZT4Zc1ewJ4JPuwb-aAEGVjqcqNVuRzmJeFAi4W5obhf1AFF7ov_WhPhUgu9yfK93uROZaIdnai6m4BUCHmkjj99co3fUx0hens9xrGaMmHAEr1kFVkSMMxw)Source: [2]The state pattern consists of three elements: a context, a state interface and concrete states. In our case the context would be **BaseController**. **BaseController** can be in one of two states: it can be displaying favourites or it can be displaying all snippets. These states have to be implemented as classes (the concrete states): **DisplayAllState** and **DisplayFavouriteState**. These classes implement the **DisplayState** interface that enforces them to have the functions needed to supply the **BaseController** class with the full list of data and the list with search results according to the state they represent. These functions are *getSearchDisplayData* en *getDisplayData*. The baseController should have instances of all the available states (in this case two). It also should have a variable called *currentState* (of type **DisplayState**) that represents the current state of the display. This variable should be set to the appropriate state when the user clicks either the “All” or “Favourites” buttons. Within the **BaseController** the listview should be loaded by either calling the *getDisplayData* function on *currentState,* if the user did not search for anything, or the *getSearchDisplayData* function on currentState in case the user did search for something.Adding new states in the future will be simplified a lot. Not many changes would be necessary. In order to add a new state for example “**DisplayPythonSnippets**” only a new concrete state has to be created implementing DisplayState. Then an instance of DisplayPythonSnippets can be added to BaseController as well as a function that sets it as the *currentState* (triggered by the hypothetical “Python” button). This solution gets rid of the complicated looking functions shown before.Examples from **BaseController** using this pattern:![img](https://lh3.googleusercontent.com/npb6Yi1Mr1ki1xXHoosoqK_Ojn6iYeE9prfXlTDaK6u6ZttvNkVWCta3MzMcl_nnBg4vZUbaEf2_U8dDpoc0Znvk09F5f-n8tGgfY9DmUmHuN9s24oNtMV7iE2X_uDsKTfa9vi1v)![img](https://lh4.googleusercontent.com/alorGV5wkg67kuLNwyQDISSXlwgbLPmBueNPzQBdmBl8iVzweaCV1ZP9xWUp5P6OR1wPvTfB_ImjzH_TpNUsCfqSsGWV22SqyhNm88FdDDi2D7wLjSaHLmtGnxVk578XfgqiCwF4) Example of a setting currentState to hold new state:![img](https://lh4.googleusercontent.com/uSMvZV3w-CSZk9UevgfO87OwjQVIhfH_681nyhJwd5ivLMhMpWX9tCEnTucaacwy8QwG9K0zzQu7o49p_Fe-mfWqQllCFDWsfliCeIGGG7fW_SWJFliGmKYjjF7IU4EloXpzZLbs) |
| Intended use       | The BaseController is instantiated when it's view is loaded. This happens when the program starts since it is the first view. After BaseController is instantiated objects of all states are created. A variable of type DisplayState is created as well. When the user clicks the favourite button the new state is first set then the display is reloaded. Now when the user starts searching the search data is fetched from the current state. Which in turn will fetch the data it needs from the **SnippetSupplier** class. |
| Additional remarks | This pattern was not covered by the course. The information needed to implement it has been taken from “Head First Design Patterns” [2]. |



| ID             | DP4                                                          |
| -------------- | ------------------------------------------------------------ |
| Design Pattern | Strategy Pattern (Comparator Interface)                      |
| Problem        | When it came to the sorting feature, sorting objects by their attributes wasn’t as straight-forward as sorting a list of strings. We had to sort a list of objects of type snippet, which were first in the form of a json array of json objects. However, there was no built in function that allowed us to sort the arraylist or the json array by a specified attribute. The feature is that the user gets to decide how to sort the displayed list of snippets. Before the implementation of sorting, the list was ordered by the date as that’s the order in which the objects were pushed to the list. If the user wants it to be sorted by name, then we need to check each snippet currently in store, and one by one order them by name. |
| Solution       | The strategy design pattern asks the very common algorithms to have a general interface encapsulating the functions required for this algorithm. In this way, if those common algorithms are needed to be used in a project, all that is needed is to implement those interfaces, and override the needed functions from this interface to meet the needs of this project. In our case, the comparator interface is the algorithm needed for the sort feature, and we implemented the compare() function in it to match each attribute to be sorted by. This allowed us to express different meanings of “sort” or “compare” for different attributes under the same object(**Snippet**). |
| Intended use   | The **Comparator** interface was the perfect fit for our situation, and it also used the strategy design pattern, making our code cleaner. We were able to create 3 separate classes that implement the *Comparator* interface, each class for a specified attribute sorting. In our case, those 3 classes are **SnippetsSortByName**, **SnippetsSortByLanguage**, and **SnippetsSortByDate**. Using this design pattern, we were able to make use of the interface which encapsulated the compare() function that we specialized for each of our 3 classes to fulfil the desired outcome. The compare() function gets called by the List.sort(), and with our now specialized compare() functions, this allows us to get the list of snippets sorted by the specified attribute. Then when the sorting function is provoked with the choice of sort, all that is done is the List.sort() function is called with the object of the corresponding comparator to that choice. |
| Constraints    | No constraints, this design pattern was the perfect fit for the sorting feature we were implementing. |





## Class diagram: 

[link here](https://drive.google.com/file/d/11ZYfYYXKNdSaMGojpPGUXDGSk6vL9nA5/view?usp=sharing)

![img](https://lh4.googleusercontent.com/9BqjJo3e1gEdqLwXiQVcwjwj-1ox8Ewc89vRQx6DbLRjiqJTNFlx45BXZjsr0dEEeD2ZoRsFH5WhFm5VCtKGJaxi9OeRd55w3JtBe_EYQHeMHQYgEnj8vW-3sUCfixU5FuAqi35H)

### models.Snippet

Description

The Snippet class is an abstraction that is used to represent the code snippets. This class is mainly used as a wrapper for json data. Controllers parse user input data into **Snippet** objects and pass it to functions in SnippetManager where they get translated into json objects (using **SnippetConverter** class) and added to the json data stored there. Controllers can also receive snippets from other parts of the system, for instance a list of snippets to display. Thus, the **Snippet** class is used to represent a snippet json entry or a new snippet created from user input. Instead of passing the contents of every text field to the controller a snippet instance is created from this data and this instance is passed to for example the **SnippetManager**.

Attributes

This class has seven attributes all set with private visibility to ensure proper encapsulation. These attributes are: *name*, *description*, *programmingLanguage*, *path* and date which are all of type String. The other two attributes are *tags,* which holds a list of Strings, and *isFavourite* which holds a boolean value. There is no code attribute because the snippet code is saved onto a file that is created when the user adds a new snippet. The path to this file is saved within the path attribute. This path is generated using *generateValidPath,* which is a function from the **SnippetIO** class (will be discussed later).

Operations

All functions of these class are getter and setter methods: *getName, getDescription, getProgrammingLanguage,  getDate, getTags,  getPath, getCode, setIsFavourite, setName, setDescription, setProgrammingLanguage, setTags*. The *getTags* and *getCode* functions have a less straightforward implementation than the others. getTags does not directly return the tags list of the object, instead it creates a copy and returns that. The reason for this is to prevent reference escaping. The *getCode* function utilises the *read* function, from the **SnippetIO,** class to read the file whose path is stored in the *path* attribute. 

Relationships

All classes except **TextInputValidator**, **Main** and **SnippetIO** depend on this class in some way. Because it is used as a wrapper for user input and json data. Dependencies of other classes on this class are not shown in the class diagram in order to maintain its clarity. Aside from dependency relationships the class has two associations directed towards it. These associations will be discussed when their respective classes are handled.

### models.SnippetManager

Description

This class holds the entire snippet collection of the user in the form of a json object. The purpose of this class is to provide controllers with the means to add, edit, delete and favourite snippets. It is involved in two of the used design patterns: singleton and observer.



Attributes

*jsonObj* of type **org.json.simple.JSONObject**, *snippetConverter* of type **SnippetConverter** and a list of **SnippetDataObserver** objects named *jsonObjObservers.* The *jsonObj* holds all the snippets of the user in the form of json entries. snippetConverter is used to convert from **Snippet** objects to json and vice versa.



Operations

SnippetManager has a private constructor namely, SnippetManager, ,this constructor has this visibility marker because the class uses the singleton pattern. Outside classes have to be kept from using the constructor to instantiate **SnippetManager** objects. The constructor initializes the class attributes: jsonObj, snippetConverter and jsonObjObservers. The jsonObj attribute is initialized by calling the private initializeJsonObject function which uses **SnippetIO** to get the contents of the json file and parses it to a **JSONObject**. 

  The public and static instance function is also an element of the singleton design pattern. It enables external classes to obtain a reference to the SnippetManager instance stored privately within the class.

  *getJsonObj* returns a shallow copy of the *jsonObj* attribute. This is done to enable outside classes to access the data stored in jsonObj but without altering its contents. This function was needed by **SnippetSupplier** and **SnippetSearcher** whose functionality is to look through the json data and fetch snippet entries that comply with particular constraints (will be discussed later).

 The *addSnippet* function is used to add a new snippet entry to the **JSONObject**. This function is public. It is passed to arguments, a **Snippet** object and the code of that snippet in the form of a string. The **Snippet** object is converted into a json entry by passing it to a function in SnippetConverter. This json entry is then placed in the right place within the JSONObject. After which, the function uses **SnippetIO** to create a file on the path specified by the **Snippet** objects *path* attribute and writes the code within it.

*editSnippet* is a public function that takes a **Snippet** Object and a code string. It works similar to the *addSnippet* function. First it updates the code file using the string it got passed. Then it resets the snippet json entry based on the date attribute of the **Snippet** object, which is registered to the second. 

*toggleFavouriteSnippet* public function that changes the boolean value of a **Snippet** object's isFavourite attribute to its opposite value. After setting the change the **Snippet** object is passed to *editSnippet* so the change can be set within the json data. deleteSnippet* gets passed a Snippet object. The file whose path is stored in the **Snippet** objects *path* attribute is deleted. The snippet json entry is removed by locating it using the **Snippet** objects *date* attribute (which is detailed to the second). This function is public.

*registerChange* is a private function that combines the updating of the json file and the notifying of the observers. It is called as the last thing in *editSnippet*, *deleteSnippet*, *andAddSnippet* in order to “register their change”.

*notifyObservers* is a private function that is part of the Observer design pattern. It loops through all observers in the *jsonObjObservers* attribute (list) and calls their callback function to notify them that the contents of the *jsonObj* attribute have changed. This function is called in the aforementioned *registerChange* function. addObserver* and *removeObserver* are both public functions that make out part of the observer pattern. They are both passed an instance of type **SnippetDataObserver**. *addObserver* will add it to the jsonObjObservers list and *removeObserver* will remove it.

 

Relationships

This class is the aggregate of objects of types: **org.json.simple.JSONObject**, **SnippetConverter** and **SnippetDataObservers**. The SnippetDataOberver interface can have multiple objects aggregated to **SnippetManager** as well as **org.json.simple.JSONObject** because they represent a part of the class that can exist independently from it.

The reason why **SnippetConvertor** is aggregated is because it was split up from **SnippetManager** to prevent the forming of a God class. The aggregation association was picked to represent the fact that it is delegated some functionality by **SnippetManager**. **SnippetManager** uses it to translate between **Snippet** java objects and snippet entries in the form of json objects. 

**SnippetSupplier** was broken off from **SnippetManager** as well but there is no aggregation relationship with it because its functionality is no longer considered part of **SnippetManager**. 

The snippetManager class is dependent on the **SnippetIO** class because it uses its static function to parse the json file in order to initialize its *jsonObj.* It also uses the static functions to write and delete files in order to complete its functionality of adding editing and deleting snippets. The *write* function is also used to save the *jsonObj* attribute onto the json file.

Dependencies on **SnippetManager** as well associations, in which **SnippetManager** is an aggregate element will be discussed when their respective classes are handled.

 

### models.SnippetSupplier

Description

Supplies lists of snippets to the DisplayAllState class and the DisplayFavouritesState class.

Attributes

Has three attributes: *snippetManager* of type **SnippetManager**, *snippetSearcher* of type **SnippetSearcher** and *snippetConverter* of type **SnippetConverter**. All attributes have private visibility. 

Operations

The *constructor* of the class, *SnippetSupplier* initializes the three attributes. The *getAllSnippets* function uses JSONPath to read all snippets from the **JSONObject** obtained from the *getJsonObj* function from the **SnippetManager** class. This function is public. *getFavouriteSnippets* is a public function that returns the snippets that have been set as favourites. getSearchResultsAll and getSearchResultsFavourites get passed a string representing a search query and return lists of snippets that are the search results. The search result lists are obtained from **SnippetSearcher**. Both these functions are public.

Relationships

Aggregates three classes: **SnippetManager**, **SnippetSearcher** and **SnippetConverter**. These aggregated elements are all delegated functionality. **SnippetSearcher** and **SnippetConverter** used to be one class, however since the **SnippetSearcher** class contained a lot of code the two where split up and an aggregation association was needed with **SnippetSupplier** being the aggregating end. **SnippetManager** was aggregated inorder to call the *getJsonObj()* function throughout the functions of **SnippetSupplier**. Finally SnippetConverter was aggregated to translate lists of **JSONObjects** into lists of **Snippet** objects.

### models.SnippetSearcher

Description

Has the functions needed to search through json data. This is done using regex and the jsonPath library. This class is package private and can’t be directly accessed by controller classes.

Attributes

*snippetConverter* of type **SnippetConverter** and *snippetManager* of type **SnippetManager**. Both attributes are private.

Operations

Contains two functions. *getSearchResults* which is public and *performTagSearch* which is private. *getSearchResults* takes two values: a String representing a search query and a string representing additional constraints. Additional constraints can be specified as static constants within the class. The constraint currently present is FAVOURITES_CONSTRAINT, when this constraint is passed to getSearchResults only the favourite snippets are searched through. The getSearchResults function searches through json data using a json path. Before returning the resulting **JSONObject** list is passed to *performTagSearch.*

  *PerformTagsSearch* checks whether there are any snippet json entries whose tags contain a tag that matches the query. If that entry is not already in the list passed from getSearchResults, it is added.

Relationships

Aggregates SnippetConverter and SnippetManager. The conversion between **JSONObject** list and **Snippet** list is delegated to the **SnippetConverter** class. **SnippetManager** is aggregated so that the functions of **SnippetSearcher** can call *getJsonObj*.



### models.SnippetConverter

Description

Snippet converter is used to translate a singular snippet to jsonObject or vice versa. It can also be used to turn a list of jsonObjects into a list of **Snippet** objects.

Attributes

Only attribute is *gson* of type **com.google.gson.Gson**.

Operations

*snippetToJsonObject* is a package private function that takes a **Snippet** object and converts it into a **JSONObject** using *gson*. *jsonObjToSnippet()* is a private function that turns a **JSONObject** passed to it into a **Snippet** object using gson. *jsonObjectListToSnippetList()* takes a list of jsonObjects and returns a list of **SnippetObjects**. The individual list entries are converted using *jsonObjToSnippet().*

Relationships

Aggregates c**om.google.gson.Gson** because it is the part that performs the conversion between javaObjects to json.

### controllers.Main

Description

This is the main class that contains the *main* function and the *start* function. The *main* function launches the javaFX application and the *start* function loads the BaseView.fxml file onto the primary stage and displays it. This class has no attributes.

Relationships

This class has two relationships, an inheritance relationship and a dependency. The inheritance relationship is because it implements **javafx.application.Application**. **Main** is dependent on javafx.stage.Stage because it gets an object of this class passed to its *start* function.

### models.SnippetIO

Description

Contains all functions needed by the system to write snippet related information onto files, read snippet information from files or deleting files. This class is package private to ensure that controllers cannot directly access files.

Attributes

No attributes

Operations

All operations of this class are static and package private. The class has all static members because it has no attributes. Making these functions static makes it more convenient to use the functions within SnippetIO. The class doesn't necessarily have to be instantiated first, it doesn't have any functions to initialize.

Relationships

SnippetIO has two dependency relationships pointed towards it from Snippet and SnippetManager. These are already covered.

 

### Models.display_states.DisplayState

Description

This is an interface that is part of the State design pattern.

Operations

This interface declares two functions getDisplayData and getSearchData. Both functions return a list of **Snippet** Objects. *getSearchResults* also takes as argument a string representing a search string. The classes that implement this interface have to implement these functions to return lists of **Snippet** objects.

relationships

The relationships connected to the interface will be dealt with when covering the other classes.

 

 

### models.display_states: DisplayAllState & DisplayFavouritesState

Description

These classes implement the DisplayState interface. Their purpose is to represent a state the display in baseController can be in. These classes make up part of the state design pattern.

Attributes

The classes have only one private attribute namely, *snippetSupplier* of type **SnippenSupplier.** This attribute is needed to reach functions within SnippetSupplier.

Operations

There are three functions: a *controller* that initializes the *snippetSupplier* attribute and *getDisplayData* and *getSearchDisplayData* are overridden from the interface the class implements.For DisplayAllState the *getDisplayData* function returns all snippets. In the case of the **DisplayFavouritesState** class the *getDisplayData* function returns all snippets that have been favourited.

The *getSearchDisplayData* function returns search results over all snippets for **DisplayAllState**. However, for **DisplayFavouritesState** the search results returned are limited to snippets that have been favourited. So, *getSearchDisplayData* in **DisplayFavouritesState** returns the results of search performed only on favourite snippets.

Relationships

This class has two relationships: it aggregates SnippetSupplier because it delegates to it the functionality of fetching snippet collections from json data. The other relationship is an extension of the DisplayState which enforces the implementation of the *getDisplayData* and *getSearchDisplayData* functions.



models.snippet_comparators: SnippetsSortByDate, SnippetsSortByLanguage & SnippetsSortByName

Description

These classes are used to compare two **Snippet** instances against each other. **SnippetSortByDate** does this on the basis of the date attribute of the **Snippet** objects. **SnippetSortByLanguage** uses the *programmingLanguage* attribute of the **Snippets** and **SnippetSorByName** sorts on the *name* attribute of the objects.

Attributes

These classes have no attributes.

Operations

The only operation each of these classes has is the override *compare* method. This operation takes two snippet objects and compares them to each other based on the methods implementation. The compare option returns an integer that indicates which instance comes before the other.

relationships

Each of these classes has an implementation relationship with the **java.util.Comparator** interface.

 

### controllers.AddAndEditController

Description

This is the controller used to control the AddAndEditView (AddAndEditView.fxml). Because the same view contains all the elements necessary for both the add and edit functionality, only one controller was used to control the gui elements of this view.

Attributes

Six of the attributes are GUI elements. *nameTf*, *languageTf* and *tagsTf* of type **com.jfoenix.controls.JFXTextField**. *descriptionTa* and *codeTa* of type **com.jfoenix.controls.JFXTextArea** and inputGrd of type **javafx.scene.layout.GridPane**. The other attributes are *snippetManager* of type SnippetManager, *textInputValidator* of type TextInputValidator and *snippetToEdit* of type **Snippet**. *snippetToEdit* represents the chosen snippet from the listview in the base controller. This attribute is set when the *loadEditData function* is called. 

Operations

The *initialize* function initializes the snippetManager and textInputValidator attributes of the class. getTextInputValidator is a public function that returns the textInputValidator this function is needed by the **AddAndEditDialog** class to perform checks on whether or not all fields of the view were filled. *handleSaveNewSnippet* parses the user's input into a snippet object and passes it to the snippet manager to be saved. The private function tagsToString parses the users string from the *tagsTf* to a list. *loadEditData* is a public function that is executed when the ‘edit’ button (from FullSnippetView) is pressed to initialize the tekst fields and textareas of the AddAndEditView with the data of the snippet to be edited. *handleSaveEditedSnippet* reads the input of the user and sets the attributes of the snippet accordingly. The snippet is then passed to **SnippetManager** where the corresponding json data is set using this snippet.

Relationships

The AddAndEditController class is composed out of **javafx.scene.layout.GridPane, com.jfoenix.controls.JFXTextField** and **com.jfoenix.controls.JFXTextArea**. The composition arrows were picked to represent the relationship between the **AddAndEditController** and the GUI elements because these elements cannot truly exist without a view to appear on.

​       The **AddAndEditController** aggregates **SnippetManager** because it delegates to it the functionality of adding and editing a snippet. The **TextInputValidator** is aggregated as well since it uses it to have its inputs checked. The controller also has an association relationship with Snippet because it has an attribute of type snippet.

### controllers.utils.TextInputValidator

Description

This class was created to divide away the input validation functionality from the **AddAndEditController.** Its purpose is to provide the functions needed to detect empty fields and to mark these.

Attributes

The only attribute of this class is a list of **javafx.scene.control.TextInputControl** objects named textInputs. This class uses a list with elements of type **javafx.scene.control.TextInputControl** because this is a parent class for both text fields and textareas.

Operations

The first operation of the class is the *constructor*. Using the *constructor,* controller classes can create an instance of **TextInputValidator** by passing to it a list of **TextInputControls**. The *controller* initializes its *textInputs* attribute using this list. The controller function is public. The next operation is *allFieldsFilled* this has a package private visibility, because it is only used within the same package by **AddAndEditDialog**. The purpose of this function is to check whether all fields represented by the entries in textInputs are filled. When there exists a field that is not filled, the function returns the boolean value false. The markEmptyfields function detects the empty fields and marks them red when called, this function is package private as well. Finally the private *underlineTextInputs* function which is used by *markEmptyFields* to mark the textInputs. This function takes a **TextInputControl,** casts it into the right type and underlines it accordingly. The function currently only casts to JSXTextField and JFXTextArea but can easily be extended to also mark other subclasses of textInputControl

 

Relationships

This function aggregates **TextInputControls** and is the type of its only attribute and is considered a part of the class that can exist outside the **TextInputValidator.** The aggregated TextInputControls can exist outside the class since they are passed to the constructor from an external class for example **AddAndEditController**.

 

### controllers.utils.DialogOpener

Description

This class is in charge of opening all dialogs needed by the system. It also provides two static functions used to simplify the creation of a dialog and the presenting of warning and confirmation messages.

Attributes

The only attribute of this class is addAndEditDialog. This attribute is private and holds an instance of the AddAndEditController class.

Operations

The constructor is public and initializes the addAndEditDialog attribute. The *userHasConfirmed* function opens an alert dialog displaying a message and returns true when the user accepts the message. This function is public and static to allow controllers to easily display popup messages to the users without having to instantiate the **DialogOpener** class. The static function *createDialog* creates a **Dialog** object, sets its basic properties and returns this instance. The purpose of this function is to prevent repetitive code. The function is static and package private because it is not used outside of the package the class resides in. The *openFullSnippetDialog* , which is public, creates a new dialog and loads the FullSnippetView onto it. The function also gets the instance of **FullSnippetController** so it can call the *loadSnippetOntoView* to load the views gui elements. After this it shows the dialog. The last two functions are public as well. *openEditDialog* and *openAddDialog* these functions get and add dialog and an edit dialog from the **AddAndEditDialog** class and display it.

Relationships

It has a dependency on **javafx.fxml.FXMLLoader** because this type is passed to one of its functions. It is also passed an **javafx.scene.control.Alert.AlertType** so a dependency relationship to that class exists as well. The final relationship is that it aggregates **addAndEditController**. This aggregation is in place because the functions of the **addAndEditController** were first part of the **DialogOpener** but because they were lengthy, they were put in their own class from where they can provide functionality to the **DialogOpener.**

 

### controllers.utils.AddAndEditDialog

Description

This class has been created to avoid the **DialogOpener** from getting too big. It contains the functions needed to set up the dialogs that load the **AddAndEditView**. This class only handles the dialog (button actions), the view itself is handled by the **AddAndEditController**.

Attributes

*addAndEditController* holds an instance of the **AddAndEditController** class. The **AddAndEditDialog** needs to access the controller functions in order to assign functionality to the dialog buttons. The *textInputValidator* attribute is of type **TextInputValidator** and is initialized by getting the *textInputValidator* from the **AddAndEditController** instance stored in the *addAndEditcontroller* attribute. The final attribute used is the enum Options which hold two constants: “Add” and “Edit”. This enum is passed as a flag to the *assignDialogActions* function.

Operations

Constructor that loads the AddAndEditView and initializes the attributes of the class. The constructor is package private because the class is only instantiated in the **OpenDialog** class which is in the same package. The *preventDialogFromClosing* function is private and has been created to keep the *assignDialogActions* function more readable. The *assignDialogActions* function sets the behaviour of the “apply” button of dialog. It ensures that when apply is pressed the textvields of the *AddAndEditView* are all filled. If this is not the case the dialog is prevented from closing. At last the class provides two package private functions intended for the OpenDialog class. These functions are getEditDialog and getAddDialog. These functions are used to hide the inner workings of the class. By providing an external dialog with an already set dialog instead of having an outside class construct the add dialog and the edit dialog themselves.

Relationships

This class aggregates two types: **addAndEditController** and **TextInputValidator.** addAndEditController is aggregated because it provides the functionality needed to set the behaviour of the dialog buttons. For example if the *assignDialogActions* is passed the option ADD. It will call the *saveNewSnippet* function within the **AddAndEditController** using the *addAndEditController* attribute. This will collect the user input and send it to the **SnippetManager**. The **TextInputValidator** is aggregated so the **AddAndEdditDialog** can check the tekst inputs of **AddAndEditController** before closing the dialog. Aggregation is used for these two types because part of the functionality of **AddAndEditDialog** is handled by them.

 

### SnippetDataObserver

Description

Interface containing the declaration of a single function, namely, *contentsChanged*. This function represents the callback function within the Observer pattern. All observers implement it to handle the change in data.

 

### BaseController

Description

The base controller is the controller for the first view that is loaded when the program starts. This class contains the gui elements responsible for the main display of the snippets. Its purpose is to display collections of snippets based on the users choices. It enables the user to choose a snippet collection. It also allows the user to search within this collection by using the searchBar. The displayed snippets can be sorted on name, date or programming language.

Attributes

 *snippetsLV* of type **com.jfoenix.controls.JFXListView,** searchBarTf of type **javafx.scene.control.TextField,** sortingCbx of type **com.jfoenix.controls.JFXComboBox**. The non-GUI attributes are currentState, displayAllState and displayFavourittesState. All three are of type **DisplayState.**

Operations

The *initialize* function adds the current BaseController object to the list of observers within SnippetManager. It initializes the sorting box, and sets the **DisplayState** attributes.

The handleAddSnippet opens the add dialog through an instance of the **DialogOpener** class. The *handleDisplayFavourites function,* called when the “Favourites” button clicked, sets the *currentState* attribute to *displayFavouritesState* and reloads the listView. The *handleDisplayAll* function, called when the“All” button clicked, sets the *currentState* to *displayAllState* and reloads the listview. These last three functions are all private. The overridden *contentsChanged* callback function reloads the listView by calling loadDisplayData. The loadDisplayData function reloads the listView with data that according to the state and whether or not the user typed something in the search bar. Before it displays the data it sorts it. This is done by calling the private function *sortDisplaySnippets.*

Relationships

Three composition relationships with the gui elements of the controller because these can not properly exist without being displayed on the view of the controller. These elements are **com.jfoenix.controls.JFXListView, com.jfoenix.controls.JFXComboBox** and **javafx.scene.control.TextField.** There also exists an aggregation relationship with DisplayState. The Base controller has two instances of this type and delegates functionality to it by having it fetch the data that corresponds to its state. BaseController implements SnippetDataOberver so that it can be added to the observer list in **SnippetManager**. Finally, it is dependent on **SnippetManager** because it gets the singleton instance through calling the static *instance* method from **SnippetManager** to call the addObserver function.

DisplayEntryController

Description

This class controls the entries within the listview that's in the Base view. Its purpose is to allow the user to favorite, view or delete snippets indpendantly. 

Attributes

It has three attributes of type javafx.scene.control.Label these attributes are: nameLbl, descriptionLbl, languageLbl. These labels are used to display the name programmingLanguage and description of individual snippets. A cellBase attribute of type javafx.scene.layout.VBox. and an attribute of type SnippetManager named snippetManager used to access the *delete* and *toggleFavourites* functions within it.

Operations

The *updateItem* function is of visibility protected and has been overridden from the superclass of **DisplayEntryController**. This function is used to load the **DisplayEntryController** onto the cells of the listview in **BaseController**. The handleViewSnippet uses DialogOpener to open the full snippetDialog this function is called when the “view” button is clicked. The handleDeleteSnippet function is called when the “delete” button is pressed; this function uses the passes the snippet to be deleted to a function in **SnippetManager.** The handleFavouriting snippet passes the snippet to be favourites to a function in SnippetManager to where the snippet can be favourited or unfavourited.

relationships

Aggregates the SnippetManager because the snippet manager handles the favouriting and the deleting of snippets for this class. The class is composed of the GUI elements it uses because These cannot fully exist without a view to be displayed on. The class extends javafx.scene.control.ListCell<Snippet>.

### FullSnippetController

Description

This class functions as a controller for the view that displays an opened snippet in detail. It gets the object of the chosen snippet and displays its information.

Attributes

The attributes of this class are *nameLbl*, *languageLbl*, *tagsL*, *descriptionLbl*, *codeLbl* and *detaLbl* all of type **javafx.scene.control.Label**. The last attribute is *shownSnippet* which holds the **Snippet** that has been selected from the listView within the BaseView.

Operations

The initialize function is used to set the current instance of the **FullSnippetController** as an observer within **SnippetManager**. This is done to make sure that the Labels of the view are refreshed after the displayed snippet is edited. The *loadSnippetOntoView* is used to load the labels of the view with the data of the *shosenSnippet* attribute. This function is public because it needs to be accessed from the **OpenDialog** class. The *handleCopySnippetCode* function is called when the “copy” button on the view gets clicked. It copies the code of the *shownSnippet* attribute onto clipboard. The *handleEditSnippet* function creates an instance of DialogOpener and opens the edit dialog. This function is called when the “edit” button is pressed. The final function is the implementation of the callback function as prescribed by the SnippetDataObserver interface. This function calls the loadSnippetOntoView function again and effectively refreshes the view after a change.

Relationships

There is a Composition relationship with **javafx.scene.control.Label**. This class can’t truly exist without being part of the **FullSnippetController**. There is an implementation relationship with SnippetDataObserver because the FullSnippetController. This is needed so that the class can add itself to the list of observers within **SnippetManager**. It also has an association relationship with snippet because it has an attribute of this type. The last relationship this class has is a dependency on SnippetManager because an instance of it is obtained within the *initialize* function.

 

 

 

## 

# Object diagrams

Author(s): Elena Ibi, Wassim Mayyasi

The object diagram looks as follows: [image link](https://drive.google.com/file/d/1A7D0AxSQZFhtIRagSubsFbujuufvNw90/view?usp=sharing)

![img](https://lh4.googleusercontent.com/Ib4t8ejJ7kBclqnrD_IwQ6VklUWbOQ10vdRSnPO5F32W174wVr393XjgcZtYEvxZvS5s61Wnodg_qhUjU88kUbS6kZ5In0x6EPkEOTxLz6-G7AUAHmDS69IXl6d2Wby2tO9ng7_o)

Object diagrams are an efficient way to describe how individual instances of classes relate to one another. More importantly, however, they provide a snapshot of a software system when it runs. Different instances of the same class might be related to one another in different ways than how they are visible. Of course, this is very different from a class diagram which covers the relation of the classes in a more overarching way. In fact, the object diagram covers an individual instance of the class; the different fields are filled with example data instead of information about how the data is maintained. Object diagrams are visually very similar to class diagrams and are fundamentally similar due to the fact that object diagrams are a subcategory of class diagrams. 

 

In this given example of an object diagram, we provide two different examples of a **Snippet** instance, originating from two different classes, namely **AddAndEditController** and **FullSnippetController**. First, let's discuss the ‘snippetToEdit’instance created in the **AddAndEditController** class; this snippet is assigned for the chosen snippets the user wants to edit. As an example, in the diagram, we showed spelling errors in the name (quick ort instead of quick sort) and description(sortingalgorithm instead of sorting algorithm) attributes of this instance, to show that the user can choose these snippets to edit these mistakes. However, mistakes could also be found in the code or the user might just want to change

the code with a better one made in which case the code would be retrieved from

the shown file path. Second, we have the “shownSnippet” instance from the **FullSnippetController** class, which exists as the name suggests, to show/display the chosen/clicked snippet. 

 

Something worth mentioning is the difference between this object diagram shown, and the object diagram shown in the previous report (assignment 2). We have effectively and

significantly changed the class diagrams from assignment 2 in order to have more specialized classes. Additionally, now we have more dispersed functionalities throughout the classes (which is discussed in the suiting section), whereas in the previous version, they were stacked almost all in a couple of classes which was the reason for some lack of clarity. These changes

lead to the new object diagram having no list of snippets in the **BaseController** anymore since we now keep the snippet objects more hidden unless we want to directly alter or view them. This brings upon those two instances of **FullSnippetController** which creates a **Snippet** instance for viewing, and **AddAndEditController** which creates a **Snippet** instance for editing; this distinction adds more clarity.

 

For simplicity purposes, we chose these examples of snippets to keep them closely connected to each other. In fact, we can see that any two snippets in the system can share the same attributes, like for example the tags, the description, and the language (not the case in the shown example) can be the same. However, in a real snippet manager, it would contain all sorts of code snippets, from algorithms, to conditional statements, to small programs and others which would make ‘sharing the same attributes’ more likely. The information related to the attributes that a user enters on the template is saved into a common JSON file. This file gets updated at run time when the user enters information about a snippet or more precisely when a user adds a snippet onto the code manager. The JSON file is composed of an array where every element corresponds directly to the snippet information (attributes)/JSON object that is added by the user. The contents of the JSON array were irrelevant to be shown in the object diagram as the latter does not consist of it and primarily because the object diagram is the reflection of possible snapshots (snippets added by user in this case) when the system runs which would thus mirror the contents shown in the object diagram.

 

​      Those shown examples are merely there to show an instantiation of these objects, however, in our system these objects are changed constantly with each new FullSnippetController and AddAndEditController created. Moreover, it is to note that code is not an actual attribute of the classes hence it is not explicitly shown in the object diagrams. What is shown, is the path of the file where the code of the snippet is stored which is the attribute shown in the object diagram above.

 



##  

# State machine diagrams

Author(s): Elena Ibi 

##  Introduction

 

State Machine diagrams are visual diagrammatic representations of the transitions that objects and other entities in a software system undergo and of the general dynamic nature of the software system in hand. A state can be thought of as the set of behaviors that an object or an entity can have at a particular time in a software system. A state transition, hence, implies the switch of an object or other entity from one state to another under certain conditions. In UML, state machine diagrams are made up of three main components and these are:

 

- The various states in which an object or an entity can live in at a particular time;
- The activities, more specifically behaviors, that an object or entity can show within a given state;
- The various events that trigger the transition from one state to another.

 

Generally speaking, the more dynamic the system is, the more states it will have and the more state transitions it will undergo. The nature of the Code Snippet Manager track does not allow the modelling of many states as the entire system was fairly static. The focus went to implement the features for a local application hence did not allow for much dynamicity.

 

## State Machine Diagram 1

 

The first state machine diagram that we designed shows the behavior of the ‘**AddAndEditController**’ class. In the following state machine diagram, we represent a single entity or object at a time, thus we consider a single snippet at a time rather than a collection of snippets for understandability purposes. The first state machine diagram looks as follows: [image link](https://drive.google.com/file/d/1Gxg-b8BVvltRPpCKc232fZxmQsHqujVc/view?usp=sharing)

 

![img](https://lh5.googleusercontent.com/WTsAjwIDMwi9_Murnbce2X-d1HGQVTSCuJuizqFU_vl15zTtnzDu3tGnhzgovaUC3NmxX4oLkY-wPXNyuQP1d2BO0uRMgGyBCLD2po6bFt93FDQQsFrbeZdchcJvAwEGjtQxpSEf)

 

The ‘**AddAndEditController**’class is a class that deals with the process behind the creation and the editing of the snippet. Briefly speaking, the user enters the details of a snippet onto the template and when he/she clicks on the apply button, a snippet object is created. This snippet object is then changed into a Json Object and placed onto a Json array contained in a file. Thus, this state diagram represents some of the concrete behaviors of the ‘**AddAndEditController**’class. The state machine diagram above shows an external transition as the transitions are triggered by events occurring outside the two states that are represented. From the initial node, represented as a black circle in the diagram, we get into the ‘not saved’ state which is a state that is entered before a snippet appears on the system. As mentioned above, this diagram shows the behavior of a single ‘snippet object’ ,in this case, when it first gets created; this implies that, although we might have other previously saved/created snippets in the system which would then place the overall system state within the ‘saved’ state, in this diagram we only depict the state of a single snippet that is to be added onto the system by the user. This representation would allow us to display the behavior of a single snippet during its creation.

The ‘not saved’ state has one entry activity and one do activity as it can be seen. As mentioned above, the ‘not saved’ state is entered when an individual snippet is in the process to be added but data has not yet been collected hence, intuitively, the entry activity is the display of the template on which the user can fill in the data related to the snippet he or she wants to add. While this state has been entered, the user is expected to fill in the required fields of the template as shown by the do activity in the diagram. Once this has been done, the user can save the data in order to actually create the snippet from the data that has been filled. The creation of the new snippet concretely happens when the user clicks on the ‘Apply’ button; the system checks whether all the required fields have been filled by the user as it is shown by the guard condition of the external call event. The checking if all the required fields were filled was a quality requirement that our system specified it would fulfill in order to not create complications later on when searching/sorting the snippets using the attributes. Nonetheless, our system does not implement a way to check the type of data that the user enters for every tag/field in order to make sure that it is in the correct format. From the diagram we can see that this external call event corresponds to the ‘*handleSaveNewSnippet*’ function call which is in charge of the collection of data and the saving of the new snippet onto the system. This function is triggered when a new snippet is firstly created and stored onto the system. The ‘*handleSaveEditedSnippet*’ is the function call that is triggered when an edited snippet is saved onto the system.

When one of these two call events (‘*handleSaveNewSnippet*’ or ‘*handleSaveEditedSnippet*’according to whether the snippet is new to the system or is an edited version) is triggered, the external transition from the ‘not saved’ state to the ‘saved’ state occurs as can be seen. As previously mentioned, the snippet object gets turned into a Json object; however, these were technical and too implementation-based details that we chose to omit from the diagram. The ‘saved’ state does not have any particular entry, do and exit activities and instead has a submachine state ‘displayed’ that gets triggered when the system enters the ‘saved’ state or more precisely when a snippet is created and hence can now be displayed. All the other features that our system implements, like sorting, searching, setting a snippet as a favorite and so on will apply once the snippet enters this state, however, these will be better shown in the next state machine diagrams with the use of composite states.

Nonetheless, one of the features we wanted to show explicitly in this state diagram is the ‘Editing’ feature as that would re-trigger a state transition back to the ‘not saved’ state. When the user chooses the snippet to edit and clicks on the ‘Edit’ button, the template used previously to add the snippet data pops up. The snippet, hence, enters the ‘not saved’ state again successfully and the user can then modify the required tags/attributes which acts as the collection of the modified data (the do activity). This call event corresponds to the ‘*loadEditData*’ function call. When the user clicks apply again (or just saves the snippet from a less UI perspective), the guard condition is again checked to see if all the required fields are still filled in after the modification. In this case, as previously also mentioned, the ‘*handleSaveEditedSnippet*’ function call is triggered as we are dealing with the editing of an already existing snippet. Once this is done, the external transition takes place and the new (edited) snippet is saved onto the system and hence re-enters the ‘saved’ state and gets displayed once more.

Another explicit feature that had to be represented within this state machine diagram, is the ‘Deleting’ feature. The addition of this feature onto this state machine diagram gives an overview of the entire snippet lifespan from its creation, to its editing and finally its deletion. Once the snippet is deleted, it ceases to exist hence can no longer perform other activities nor any of the other features will apply to it. Therefore, as can be seen in the diagram when the user deletes a specific snippet, the ‘*handleDeleteSnippet*’ function call gets triggered. The user is then asked to confirm his/her decision by either clicking on the ‘ok’ or on the ‘cancel’ buttons. Only if the user clicks on the ‘ok’ button (as can be seen from the guard condition) the snippet will be deleted successfully. This deleted snippet will no longer exist hence will no longer be displayed; this is represented by the arrow that goes from the ‘saved’ state to the termination node.

## State Machine Diagram 2

The second state machine diagram is a composite state diagram that represents three sub-states that undergo external transition from and to each other; they all live within a bigger super-state. The bigger super state is the ‘displayed’ state and the three closely related sub-states are ‘sorted by default (Date Added)’, ‘sorted (by Name)’and ‘sorted (by Language)’. The ‘displayed’ state is the submachine of the state machine diagram 1 as it gets triggered when the snippet enters the ‘saved’ state as explained previously. The state machine diagram looks as follows: [image link](https://drive.google.com/file/d/1BaiuLCWHEGq6fquITQuqlaGq7FsBmvwJ/view?usp=sharing)

![img](https://lh3.googleusercontent.com/V6ECvo5w04J_ft2KSUAiinrdjRTTSk4VRt8jHnbVlQaH3DHvd6yxHx61LsijFJtLabjEvhipPvVcxazozQgxa2qZRaORVnOkJMC4m2v7zJgGbqat1JkkDJ6qnMLFi9l1qxYAVkD3)

   This state machine diagram shows the behavior of the ‘**BaseController’** class as it mainly focuses on the representation of the state transitions that the snippet undergoes when displayed and sorted by the three available tags (Date, Name and Language); all operations that are dealt by the ‘**BaseController**’ class along with other classes. The initial state node leads to the first state that can be entered, namely the super-state ‘displayed’. There is one single entry activity, that of adding a snippet, which intuitively will allow the snippet to be displayed on the front page of the code snippet manager, thus entering the ‘displayed’ state. The notion of the representation of a single entity at a time does not apply anymore in this state diagram as all the snippets will be displayed but more importantly because the ‘sorted’ sub-states, shown above in the diagram, would not make any sense if we only consider one single snippet at a time. There is a second initial node that will lead to the ‘sorted by default’ state which is the one sorted by ‘Date Added’. When a snippet is added onto the system for the first time, it is by default sorted by date hence will automatically enter the ‘sorted by default(Date Added)’ state. The user has then the possibility to choose to sort the snippets by using other tags namely ‘Name’, ‘Language’ or the Date Added(default) tags (this option will be possible for the user to choose if the snippets were first sorted by the other two available tags). In other words, the diagram shows the default state just to imply that it is the state that the system will be in at the start if no sorting state transitions have been triggered yet, or more precisely if no sorting operation has been performed yet. When the user clicks on the sort button and chooses which tag to sort the snippets by, this event triggers an external transition from the ‘sorted by default (Date Added)’ state to the other two sorted states according to the tag chosen by the user.

​      The two options that are readily available for the user are shown by the use of a decision node. The decision node was not present in the previous report since ‘Sorting’ was not the feature we implemented and hence we decided to not get prescriptive on the attributes that the user can choose to sort the snippets by. When the user wishes to sort, he/she is presented with a drop-down menu that shows the three available options. If we consider the system to be in the default state, then the user will have two options. Regardless of which tag is chosen the same ‘*sortDisplaySnippets*’ function gets called and now depending on the tag chosen, shown as a guard condition for the two external transitions that go from the default state, the corresponding state of either ‘sorted(by Name)’ or ‘sorted(by Language)’ are entered. The user is free to change the sorting of the snippets at any time while using the system and hence this is shown in the diagram above. The system can go back to the default sorting state from any of the other two optional sorting states. The two arrows that go from one optional sorting state to the other and vice versa also show that the user can sort the snippets by ‘Language’ and then by ‘Name’ or the other way around. In all of these state transitions, the call events trigger the same function call (*sortDisplaySnippets*) and the only thing that varies is the tags chosen by the user that act as guard conditions for every entry of the three states. In all of the three sub-states, we only showed two ‘do’ activities, namely ‘Edit’ and ‘Copy’ the snippets; this is because, although the other features such as ‘Favourites’, ‘Searching’ and so on apply when the snippets are in any of these three sub-states, having all of the features as ‘do’ activities will imply constant editing and updation of the snippets which are operations that do not necessarily occur while the snippets are in these three sub-states. Thus, for visibility purposes, we chose to stick to two of the many features that could apply to the snippets while in these sub-states. 

The exit operation or activity is the deletion of a snippet or more snippets as they will not be displayed anymore; whether it is a single snippet or multiple snippets is not an issue here again as the state representations apply to both the scenarios. The last component of this state diagram is the final state node which shows the end of the sequence of states. 

## State Machine Diagram 3

The third state machine diagram shows the state transitions that one or multiple snippets can undergo when they are marked as favorites by the user. It is a functionality that has been implemented in the ‘**BaseController**’ class. We thought that it would be necessary to separately show this state transition as it would change the display of the snippets; the favorited snippets will still appear on the main front-page display but also on the ‘favourites’ section. The state machine diagram looks as follows: [image link](https://drive.google.com/file/d/1nKqUH43SQcum8CVpmeJpUQ4Xa9-acMc9/view?usp=sharing)

![img](https://lh4.googleusercontent.com/GjjZuRXUbDW-Va1Pvo3C-iWzjUQjCIvByISUIo8VviFmxrIf1G4rL-6dwkN-f8IUNX2WvNAQbVJ7FhSdR651w7OvQpeveHi7Bc4E8Iz-Tvj40Er77bkvJkD5oHgmuuPtiTxQ_qpu)

​      By default, every snippet that is added by the user on the system is in the ‘not favourited’ state as setting a snippet as a favourite snippet is a choice that is left to the user. In fact, the entry activity of the ‘not favourited’ state is the addition of a snippet or multiple snippets onto the system. When the user selects the snippet and clicks of the ‘Favourite’ button, this event triggers the ‘*handleDisplayFavourites*’ function call as shown by the external event on the diagram above. This will effectively transition the state of the snippet from the ‘not favourited’ to the ‘favourited’ state. The only concrete change that occurs after this state transition is the additional addition of the favourited snippets onto a dedicated ‘Favourites’ section on the manager. These favourited snippets will still appear on the main front-page display without any change.

​      The user can also re-select the previously favourited snippet and choose to unfavourite it from both the main display and from the ‘Favourites’ section. This event will trigger the transition of the state of this snippet back to the default ‘not favourited’ state as shown by the second arrow in the diagram above. For the same reasons as in state diagram 2, we also decided to just stick to the same two ‘do’ activities, regardless of the various other features/activities that would apply when the snippets are in either of the two states shown above. 

 

 

 

## 

# Sequence diagrams

Author(s): Wassim Mayyasi

## Introduction:

In this section we show the sequence of events that occur in the back-end when the user interacts with some of our main implemented features. Those features are add, delete, favouriting and editing. We do not show or go into details of what we have not implemented ourselves, being functions that we used from imported libraries. Also, for all the diagrams, we do not show the details of what happens with the display, we end it with a call to the function that deals with the display without going in depth into that feature implementation. This is the case to focus and show only the *interaction partners* that are dealing with each specific feature without going broad to another feature, in this case the display situation. And now with all the project implementations being overall complete, and our class diagrams finalized and specialized clearly, these sequence diagrams might seem a little more bulky and descriptive.

For each diagram, we discuss the behavior of the system for each feature and the exchanged messages between all the involved interaction partners. Additionally, the activity of some interaction partners is discussed along with the length and most active interaction partner. All sorts of messages are shown in the diagrams and mentioned to point out in the text for the specified message. Several combined fragments are also displayed in the diagrams and discussed in their respective text. There is also a clear increase in what all those sequence diagrams looked in the previous report to this report. This is the case because in the previous report we were still in the modeling stage for most of those diagrams, and as we started implementing, we found out things we have not thought about while modeling.

A **disclaimer** before the beginning of this section: there are no specifics of implementation mentioned, or any details shown or reasoning. We stayed as general as we could in this section, stating only information with relation to sequence diagrams. The reasoning of decisions, and why certain functions exist will be explained either in the class diagram or the implementation section. Also, specifically for the repetition of certain series of functions, that is explained in the implementation section below.

 

 

 

 

 

## **USER ADDS SNIPPET:**

[image link](https://drive.google.com/file/d/1MYF5OA2icdgnd2E6FVBp-L0eHavvP9i_/view?usp=sharing)![img](https://lh4.googleusercontent.com/EjmG--XopYMAzGhMmOyqqG_KI22Qb4-S9FTyenJKwNwtvj_K6zI0y5Z-ORLJHjxLJdhu07QuJPBSawQcsXF-0fWLXwdXbM3PW53TSNvTkoGYdyXjyHp6CuZWJaIUlrWcrfynAzAH)

Adding a snippet is a fundamental feature of the code snippet manager, and it is the very first feature that was implemented in this project as can be seen in the previous report. The add action is done on the created Graphical User Interface (GUI) implemented using **JavaFX** and **JFoenix**. The shown sequence diagram has the series of function calls (events) that happen when the add action is initially provoked or started. It also shows the *interaction partners* for these events, as well as many types of *messages (asynchronous, synchronous, create, etc).* Also, some prescriptive functions are shown, but the functions that we have not implemented ourselves and are just used from an imported library, are not shown. Some of these functions include the GUI functions that set the dialog, also the function that parses the snippet type to a json type, and the add function that adds that json object to our stored json array. The *interaction partners* in this sequence diagram are: **BaseController**, **DialogOpener**, **AddAndEditDialog**, **TextInputValidator**, **AddAndEditController**, **Snippet**, **SnippetIO**, and **SnippetManager**. 



This sequence diagram starts with the user’s action, the user interacts with the “home page” or the base user interface controller(**BaseController**). The user initially starts by clicking on the “Add” button, and this clicking event in turn calls the function *handleAddSnippet()* from the **BaseController**. This function creates a new instance of (*create message* shown)**DialogOpener**. In the constructor that initializes this instance, an instance of **AddAndEditDialog** is created (*create message* shown here as well)as well. Then in the **AddAndEditDialog** constructor, a *createDialog()* is called back to **DialogOpener** which starts the creation of the dialog that will be shown to the user and returns (using a *synchronous message* and *response message*) it to **AddAndEditDialog**, now saved under ‘dialog’ attribute. After all these instantiations, we’re back in the **BaseController** which will call *openAddDialog()* from **DialogOpener** so that the user is shown the dialog. The dialog is currently held in **AddAndEditDialog** under the ‘dialog’ attribute, so *getAddDialog()* gets called to return the dialog. Next, we see a *message to self* in **AddAndEditDialog**, the function *assignDialogActions()*, and then finally the dialog gets returned all the way back to the user. The user can now fill in the dialog for the new snippet. Next, we have a *combined fragment* with an *optional operator*, which in this case, covers the whole remaining messages for the rest of the sequence diagram. This *opt fragment* is covered by the *guard* which is the user pressing the Apply button, if the user clicks the cancel or X button, the remaining functions are ignored and the dialog is exited. However, if the *guard* [button pressed was Apply] was true, then we move on, by first checking if all the fields were filled, using the function *allFieldsFilled()*. This is called by **AddAndEditDialog** and sent to the **TextInputValidator** class. Following this is another fragment, however this time it is an *alternative operator*, this has 2 *guards*, one of [not all fields filled] and the other being of [else]. First, in the *operand* of [not all fields filled], the *preventDialogFromClosing()* is called in **AddAndEditDialog** as a *message to self*. Then another message from **AddAndEditDialog** to **TextInputValidator** is sent as *markEmptyFields()*, and this concludes this *operand* of the *alternative operator*. However, for the *operand* of the [else] *guard*, the **AddAndEditDialog** has its last message in this sequence diagram ending its active state. The function *handleSaveNewSnippet()* is called to the **AddAndEditController** class, with its first action being a *create message* to a ‘newSnippet’ instance of **Snippet** class. The creation of this instance results in a *synchronous message* between the **Snippet** class to the **SnippetIO** class, *generateValidPath()*. Following it is the *response message* to set the ‘path’ attribute with the result of the function call. After the constructor from **Snippet** returns, we are back in the **AddAndEditController** in *handleSaveNewSnippet()*, and the function *addSnippet(newSnippet, codeText)* is called to **SnippetManager** as an *asynchronous message* ending **AddAndEditController**’s active state**.**  In **SnippetManager**, it first sends an asynchronous message to **SnippetIO** of *write(newSnippet.path, codeText).* Then, **SnippetManager** has a *message to self* , *registerChange()* called. Following it, is another similar to the earlier *asynchronous message* to **SnippetIO** with the function name *write(jsonFilePath, jsonObj)* however, as shown with a different parameter. This ends **SnippetIO**’s active state, and following it is yet another *message to self* in **SnippetManager** of *notifyObserver()*. Then finally, the control is returned all the way back to **BaseController** with *contentsChanged()* and there a final *message to self* of *loadDisplayData()* ends the entire sequence of events for this adding a snippet feature.



Alternative:



We were bouncing ideas around whether we want to store the added snippets as json objects or just keep them as **Snippet** instances. The thing with using something other than json objects is that we would have needed to reinvent the wheel by creating a function that would add these snippet instances to a file, while json libraries already had functions that fulfill this purpose. This alternative implementation also proved not useful for all the other features we have implemented, where we would have needed to create functions that fetch, find, delete and so on, where these functions are already in existence for json objects. So, using JSON objects from our point of view seemed the most efficient solution.

## **USER DELETES SNIPPET:** ![img](https://lh5.googleusercontent.com/KyeFtQLkhhY0ct_uQXTwfhZOhNOV2BOxvitol_zFzQ5--Qwbo7Fnk8oGgarCxk3dLa2dptLSu5zoOjwUjx9wHNTtH0JpSU9sj8i2z-byHhU7hAZgcrDbVm2SFq_brbVkZIc5BGpm)

[image link](https://drive.google.com/file/d/14bysqtxS2ka2tOvGIcX98oxvH3SGFUBF/view?usp=sharing)

Also as part of the code snippet manager, we believe that the delete feature is a requirement for the user of such an application. There must be an opposing function to the add function, so when the user wants to revert his action, for whatever reason it might be, it is made possible. This opposing function would be the delete. The way our system stores the snippets is quite simple with the usage of the json file and the text files with the snippets’ code in them, this helps us easily extract and delete the chosen snippet from the file. Over this whole sequence diagram, the interaction partners are the **DisplayEntryController**, **DialogOpener**, **SnippetManager**, **SnippetIO** and **BaseController**, however, this is with disregarding the user as an interaction partner that actually initiates this entire series of events.



As just mentioned, in the previous sequence diagram as well, this sequence also starts with an action taken by the user, and here the series of events start with the user clicking the delete button on the snippet they would like to remove. This action provokes the function *handleDeleteSnippet()* found in the class **DisplayEntryController**. The first thing done is an alert to the user using the function *userHasConfirmed()*, which prompts the user to reassure them of their actions and ask for their confirmation, this is done to serve our quality requirements to make our system more reliable. This function is called as an *asynchronous message* to **DialogOpener**, which gives the confirmation alert to the user. Following the alert, we have an *optional operator*, which covers the rest of the sequence diagram, having a *guard* of [user presses OK](**note**: a rephrase). The result is given back to the **DisplayEntryController**, which if the *guard* evaluates to true, now ends its active state by calling the function *deleteSnippet()* as an *asynchronous message* to **SnippetManager** starting its active state. In *deleteSnippet()*, it first sends a message to **SnippetIO** of *deleteFile()* and that starts the active state of this class. Then, as a *message to self* occur in **SnippetManager**, being *registerChange()*. However, something missing from the sequence diagram before the *registerChange()* message is the .*delete()* call in the **JsonPath** library as that isn’t implemented by us, this will be explained below in the implementation section. Next there is a message *write(jsonFilePath, jsonObj)* to **SnippetIO** and that is also *asynchronous*, ending **SnippetIO**’s active state. Finally, the last 2 messages in **SnippetManager**, the first being a *message to self*, *notifyObserver()*, and following it is a *contentsChanged()* *asynchronous message* to **BaseController**. That last message ended **SnippetManager**’s active state and briefly started the active state in **BaseController**, had a message to self of *loadDisplayData()*, then ended its activity in this sequence diagram. And this concludes this rather brief sequence of events for this delete a snippet action.



Alternatives:



At some point, we saw potential in keeping in store all the deleted files instead of actually deleting them. So, whenever the user wants to delete a snippet, we would store this snippet in a sort of internal “recycle bin”. This would have been helpful in case of a user regretting the deletion of a snippet and wants it back. However, this felt a little inefficient in the long run. For the case of it being helpful when the user has regrets, we decided to implement the reassurance message that gets displayed to have the user be sure of their actions which then made the delete feature irreversible.



Another very small alternative was regarding the **DisplayEntryController**. At some point, we wanted to include all the buttons at the top of the front page (in **BaseController**), and the user would select the snippet and then click on the button corresponding to the action they want to take like “Delete” or “Edit”. However, this was not chosen because it made the user interface inefficient, while having the button for each snippet showed a better interface where they just clicked 1 button instead.

## **USER FAVOURITES SNIPPET:** ![img](https://lh6.googleusercontent.com/Mw3c3akh664T2C-K3p4pdzFFfonNX2_qO8EivDr54aWFkuGw8w0-mR8R2SYQyDwHzTm0JZqGhXqcmpoeUxnxsHmz_CsLeM1RTd4t8gqQheialojJWHvXy8VVk-xKZDQxxVgsc_Ac)

##  [image link](https://drive.google.com/file/d/1IUDuXrWlauPjgvT6olRyff35I2i_4aKi/view?usp=sharing)

When deciding to create the favouriting feature, we knew it was not as necessary as the other features, it was just an additional functionality for our system to have. The interactions in these sequences of events are carried on by the **DisplayEntryController**, **SnippetManager**, **Snippet, SnippetIO, SnippetConverter** and **BaseController**. For each snippet, there will be a button that can be used for favouriting. Upon pressing this button for one of the snippets, the series of events described in the sequence diagram will hold, without mention of the display situation, as that is a feature itself. This action might have an effect on the main page in case the favourites display was the one showing, so **BaseController** needs to be messaged as well. Also, this means that the events occurring in this sequence are all back-end functions, in which this would make it harder to tell whether the action succeeded if the displayed list is “All Snippets''. However, this change can be of course checked by opening the list of favourites from the **BaseController**.



At start, the user clicks on the button designated for the favouriting of snippets. This action fires the function *handleSnippetFavouriting()* from the class **DisplayEntryController**. As previously mentioned in the class diagram section, **DisplayEntryController** is responsible for each individual snippet having their own designated buttons for each feature, in this case it’s the favourite button and the *handleSnippetFavouriting()* function call. However, **DisplayEntryController** does not deal with the functionality of changing or mutating the attributes of a **Snippet**, so the snippet is passed to the **SnippetManager** class as a *toggleFavouriteSnippet()* message. The plan for the setting of the favourite is chosen to be very intuitive. First, we get the current assigned value of the attribute “isFavourite” of our selected snippet, using *getIsFavourite()*, this call goes to the **Snippet** and returned to **SnippetManager**. Then all what’s done is we negate that returned value into the parameter of the set function as so: *setIsFavourited(!isFavourite)*. After this full interaction happens between **Snippet** and **SnippetManager**, the “isFavourite” is now set as the new value either True->False or False->True, but in our case the action of this sequence diagram is favouriting a snippet, so it is False->True. Next, the snippet has been edited, however, the json file needs this edited snippet to finalize and update internally. So the **SnippetManager** takes this newly favourited snippet, and *messages to self* with the *editSnippet()* function. Here, on the back-end the favourite is finalized by editing and updating the json object and then the json file respectively. First, the snippet path and the snippet code is passed from **SnippetManager** to the *write()* function in **SnippetIO** as an *asynchronous message*. Next, there is a *snippetToJsonObj()* with the snippet instance passed as a parameter as a *synchronous message* from **SnippetManager** to **SnippetConverter**. With a *response message* back, and setting the value return on a local variable ‘newSnippet’. Following that, a similar series of messages are sent as mentioned for the previous sequence diagrams, starting with the *message to self,* *registerChange()* in **SnippetManager**. Then there is the *write()* function to **SnippetIO** with ‘jsonFilePath’ and ‘jsonObj’ as parameters, ending the active state of the **SnippetIO**. After this there is a message *write(jsonFilePath, jsonObj)* to **SnippetIO** and that is also *asynchronous*, ending **SnippetIO**’s active state. Finally, the last 2 messages, the first being a *message to self* in **SnippetManager**, *notifyObserver()*, and following it is a *contentsChanged()* *asynchronous message* to **BaseController**. The last message ended **SnippetManager**’s active state and briefly started **BaseController**’s active state with the message to self of *loadDisplayData()* and ended its active state in this sequence diagram. And this concludes the sequence of events caused from the action of favouriting a snippet.

## 

## **USER EDITS SNIPPET:**![img](https://lh3.googleusercontent.com/gHfBVy8vHHNn6HE7Y7a7UEe74bB8kGiD2BPOfnD6QKvRak_vA-BStGVSRyIO8-M6wmXDUkFnJleIWZ_B_zb3wlaCa3DtozulrakZtVf1WrK1Js87JwkRFEEqCDUlFapqFvPml05f)

[image link](https://drive.google.com/file/d/1sPGwA7gFSi09ZWcVjb-pyOib1u2ATh0v/view?usp=sharing)

Another very important action to exist in a code snippet manager, that adds comfort and ease to the use of the application ,is the editing of the snippets. This feature would allow the user to quickly update their old code snippet, or in case of errors in the description or tags, fix the attributes of a snippet. When viewing a snippet individually, there will be a button “Edit” that allows this function, and the shown sequence diagram to take place. There are steps throughout the implementation of this feature that are added just to allow the system to follow some quality requirements for usability and reliability. An example of that is the reassurance dialog, or in case of empty text fields, the user will get notified by means of an alert as well. The *interaction partners* in this sequence diagram as shown are, **FullSnippetController**, **DialogOpener**, **AddAndEditDialog**, **AddAndEditController**, **TextInputValidator**, **Snippet**, **SnippetManager**, **SnippetIO**, **SnippetConverter**, and **BaseController**, and with that the most *interaction partners* in all of the shown sequence diagrams in this report.



To begin with the editing action, the user starts by clicking the edit button in the view of the chosen snippet. This click calls the function *handleEditSnippet()* from the class **FullSnippetController**. Inside the function, it starts by creating a dialog similar to the one that was used to add the snippet, by creating a **DialogOpener** instance. Again, similar to what happened in the add sequence diagram above, it is followed by another *create message* to an instance of **AddAndEditDialog**. Then a *synchronous message* is sent back to **DialogOpener**, and a *response message* back to **AddAndEditDialog**, being *createDialog()* and the returned value respectively. This completes the initializing of the **DialogOpener**, and it is now back in the *handleEditSnippet()* in **FullSnippetController**, where the next message is *openEditDialog()* to **DialogOpener**, and following that is *getEditDialog()* from **DialogOpener(**ending its active state**)** to **AddAndEditDialog**. Next, loading the data of the snippet is needed, this is done by calling the function *loadEditData()* with the parameter of the snippet. The load function, which is called to the **AddAndEditController** class, sets all the text fields of the dialog to their respective value from the snippet that was passed as argument. Lastly, before the user is given this dialog to fill, the **AddAndEditDialog** makes a message to self with *assignDialogActions()*, setting the title of the dialog which is passed as a parameter, and certain event handlers. 



The rest of the sequence diagram following the dialog finally being shown is covered by an *optional operator*, which means if the user doesn’t trigger this *guard*, the following messages are not sent. It starts with the [user pressed Apply](**note**: this is the meaning of it) condition/*guard*, then an *asynchronous message* is sent from the **AddAndEditDialog** to **TextInputValidator** to check if *allFieldsFilled().* Then this provokes another fragment, this time the *alternative operator(alt)*, which starts with the guard for [not all fields filled]. In this case, there is a *message to self* in **AddAndEditDialog**, and following it is an alert to the user to fill all fields, and then an *asynchronous message* to the **TextInputValidator** of *markEmptyFields()*. This ends the first guard’s branch, and then follows the case for [else] condition, which refers to if all the fields were filled. Here is where the snippet is added to the system, it starts by **AddAndEditDialog** ending its active state by sending to the **AddAndEditController** a message of *handleSaveEditedSnippet()*. Firstly, there is a series of setter functions, *setName(), setDescription(), setProgrammingLanguage()* and *setTags()*, all sent to the class **Snippet** with the instance ‘snippetToEdit’. Afterwards, this snippet which was just set ‘snippetToEdit’ is passed with the message *editSnippet()* to the **SnippetManager**. In **SnippetManager**, it first sends an asynchronous message to **SnippetIO** of *write(newSnippet.path, codeText)*. Next, the **SnippetManager** messages **SnippetConverter** making it active briefly, with *snippetToJsonObj()*. Then, **SnippetManager** has a *message to self*, *registerChange()* is called. Following it, is another similar to the earlier *asynchronous message* to **SnippetIO** with the name function *write(jsonFilePath, jsonObj)* however, as shown, with a different parameter. This ends **SnippetIO**’s active state, and following it is yet another *message to self* in **SnippetManager** of *notifyObserver()*. Finally, the **SnippetManager** calls *contentChanged()* first to **BaseController** briefly starting its active state, where it *messages to self* with *loadDisplayData()* and ends its active state, then messages *contentChanged()* all the way back to **FullSnippetController**, where it also *messages to self*, however with *loadSnippetOntoView()*. This ends this sequence of events provoked by the editing feature all the way to the display (omitting the display details).

# Implementation

Author(s): Nour Oujjit, Wassim Mayyasi

Location main class: src/main/java/controllers/Main

Location Jar file: software-design-vu/build/libs/software-design-vu-2020-1.0-SNAPSHOT.jar
**Note**: This jar was built using the ‘gradlew jar’ command from the terminal. The other method using the artifact was not working out with us, whenever we ran it, we got no responses. But now, this jar file works correctly.

Video: https://youtu.be/f67vNy9LIjo

## From UML models to code

After the initial idea was modeled the implementation process started. Based on the class diagrams we formulated the object and state diagrams. These diagrams together gave us better insight in the sequence of actions that need to be taken for the user to perform a particular task. This enabled us to draft the sequence diagrams. After we had the initial idea on the structure of the system the implementation process began. After implementing the Add feature alone last time, we kept doing the same thing for the rest of the features. When things didn't work out as planned in the models whilst writing the code, the code was refactored and the diagrams were adjusted to model the new situation. After which the models were expanded using the new insight of the system. When the code was finalized we made sure to work away any discrepancies from the models.

## 

## Example of the json structure used

This example displays two snippets.

{

  "snippets": [{

​     "date": "2020-04-02 21:52:35",

​     "path": "snippet_files\/1936620467.txt",

​     "programmingLanguage": "Java",

​     "name": "mySnippet",

​     "description": "This is my first snippet",

​     "isFavourite": false,

​     "tags": ["array", "list"]

  }, {

​     "date": "2020-04-02 21:53:09",

​     "path": "snippet_files\/1460930924.txt",

​     "programmingLanguage": "loop variables",

​     "name": "Loopy",

​     "description": "this is an example of loopy code",

​     "isFavourite": false,

​     "tags": ["Python"]

  }]

}



## Implementation done for add:

Our snippets are stored in a json file a json object containing the array of json objects, so for any feature that would need those snippets, we would have to retrieve the json object that contains the array and convert it to a Snippet object. **SnippetConverter** class contains the functions(*jsonObjToSnippet()* and *snippetToJsonObj()*) that convert json objects to Snippet objects and a function that does the reverse(at the end of the edit, once it's done). This begins with the function that captures this action in the **BaseController** *handleAddSnippet()*. Then the function *handleSaveNewSnippet()* from **AddAndEditController** is used to create a new **Snippet** instance, with all the filled in values the user inserted into the text fields as attributes. For the path file, the implementation was changed to what it previously was, we decided it was best that the *generateValidPath()* now just generates a randomized number from 0 to the maximum integer java can have, and sets the file name as that. Then it passes this new snippet to the **SnippetManager** by *addSnippet()*, as well as the code text fields so that it gets stored to its appropriate file path as well. In *addSnippet()*, we use **JsonPath’s** .*add()* function to add the snippet right after converting it using the *snippetToJsonObj()* from **SnippetConverter**. Next, we call the .*write()* function from **SnippetIO**, which handles any writing to path using the **FileWriter** library, here to write the code that was passed to the path that was generated. The last function to finalize the addition, we have the *registerChange()* function which gets called often in this class. The reasoning behind this function being constantly called is because in the code, we do not work directly on the json file, but the json object instance we have inside the **SnippetManager** *jsonObj*, so when we .set() or .*delete()* or .*add()* we are setting and deleting this json object of json objects, and that needs to now be written on the json file to update it.

## Implementation done for edit:

To capture this feature, it starts from the **FullSnippetController** with the *handleEditSnippet()* function. Now we implement in **AddAndEditController**, we first put those old values that we got from the old snippet onto the screen using the *loadEditData()* for the user to change those values. Then once the user does their editing, first we check whether all the fields were filled using *allFieldsFilled()* from the **TextInputValidator** class, then we take in those new values using *handleSaveEditedSnippet()* and set this snippet with its new values. Now that we have an edited snippet, we still need to finalize the edit, and this is done in the **SnippetManager** class using the *editSnippet()* function. We then call a *write()* from the **SnippetIO** mentioned above, but here to update the code before converting the snippet object back to a json object(using the function mentioned above in **SnippetConverter**). Now, we have to update the json file, and this is done by first finding the json object corresponding to the snippet, by the date. Using the date, we can use JsonPath built in function .*set()*, we can use that to edit an existing json object. Finally, we have to *registerChange()* and this calls again the .*write()* in **SnippetIO** to write into the json file the full edited json object. 

## Implementation done for delete:

This is started from **DisplayEntryController** which contains all the buttons for each entry of a snippet contained in the list, with the *handleDeleteSnippet()* function. First, to keep our system reliable, we implemented a confirmation alert here that gets called with *userHasConfirmed()* from the **DialogOpener**. After confirmation, to carry out the deletion of the snippet however, this is passed to the **SnippetManager** with *deleteSnippet()*. Here, we first start by deleting the text file that contains the code from the by using *deleteFile()* in the **SnippetIO** which in turn uses the *.delete()* function of **File** library. Then, again, we use the date as previously mentioned to point out the exact json object in the list of json objects, and that json object is deleted using the .delete() from the **JsonPath**. And lastly, similar to the add and edit, the *registerChange()* is called here as well. 

## Implementation done for sorting:

The portion of sorting that deals with the strategy design pattern was discussed above, we will discuss the details of the implementation here. The function *sortDisplaySnippets()* is called from the **BaseController** which takes the value of the *sortingCbx* (combobox), according to the user’s choice. Then for each of the choices, “Date Added”, “Name”, or “Language”, it calls the *.sort()* function from List library, and passes as a parameter an instance of the classes that implement **Comparator**. For each of the choices mentioned, there are the corresponding classes **SnippetsSortByDate**, **SnippetsSortByName**, and **SnippetsSortByLanguage**. All three classes override the function *.compare(),* which is what is used in the *List.sort()* function. In each function, it parses the values it’s comparing to their original data type, in this case *LocalDateTime*, *String* and *String* respectively. Then uses the built in *compareTo()* for the Date and *compareToIgnoreCase()* for the two strings. And because Lists in java get passed by reference, the list gets modified at *.sort()* call.

## Implementation done for favourites:

  For this feature, there are only a few functions that were implemented as it is quite a simple feature to have. We have the ‘isFavourite’ attribute in the **Snippet** class, which has a boolean value of true or false for favourite or not. When the user wants to favourite a snippet, he clicks on the favourite button for the chosen snippet and that fires the implemented function *handleFavouritingSnippet()* from the **DisplayEntryController**. Here, we pass the control to the **SnippetManager** to *toggleFavouriteSnippet()*. The logic behind this portion is for it to toggle favourite and unfavourite in this function, so it will retrieve the current isFavourite value, and inverse it by using !isFavourite. So it gets the ‘isFavourite’, and sets its inverse, then to finalize this change, the json object gets updated by calling *editSnippet()* as mentioned above.

## Implementation done for copying:

For this feature, the implementation is very straight-forward, the function in **FullSnippetController** handles the copying immediately at button press. The function is *handleCopySnippetCode()*, and it uses the **StringSelection** and **Clipboard** libraries from the java.awt. It first passes the code from the full snippet selected to the **StringSelection** instance. Then creates a **Clipboard** instance and passes the **StringSelection** instance to the function *.setContent()* in the **Clipboard** class.



## Implementation done for searching:

Searching has been implemented within the **SnippetSearcher** class. This class has two functions one for searching through the attributes of snippets that are non-lists and a special function that searches through the tags (Because searching through lists couldn’t be implemented in the same way). These functions are *getSearchResults* and *performTagSearch*. 

performTagSearch adds to the search results that were found by searching only through non-list elements.

Searching was implemented on json data. The JsonObject storing all the snippets is obtained from **SnippetManager** using: 

snippetManager.getJsonObj(). 



This json data is passed to a jsonPath library function together with a string that represents the path:

 

ArrayList<JSONObject> searchSnippetsJson =

​        JsonPath.read(snippetManager.getJsonObj(), searchPath);



This code sets searchSnippetsJson with a list of JSONObjects. These JSONObjects are obtained from the given path. The path we used is:



String regexStr = "/^.*" + searchStr + ".*$/i";

String searchPath ="$.snippets[?((@.name =~ " + regexStr +

​        " || @.description =~ " + regexStr +

​        " || @.programmingLanguage =~ " + regexStr + " ) "+

​        additionalConstraint + ")]";



By passing this string to the read function all objects that have a name or a description or a programmingLanguage that matches the given regex are collected. This regex string "/^.*" + searchStr + ".*$/i" finds any string that has the value of searchStr in it.



Example:

$.snippets[?(@.name =~ /^.*test.*$/i)]

This path would return all snippets whose names have the word test in it. 



The additional constraint can be the string "&& @.isFavourite == true", this string is declared as a constant static variable up in the class.. It can be passed to getSearchResults to only search on favourite snippets. If no additional constraint is needed an empty string, “”. can be passed instead



After is searched on name, description and programmingLanguage. The list with search results is passed to performTagSearch. Tag search obtains a list with all existing tags using jsonPath:



 ArrayList<String> allTags = JsonPath.read(snippetManager.

​        getJsonObj(), "snippets[*].tags[*]");



It then loops through this list and checks every tag to see whether it matches the regex expression given. The String.match() function is used for this. If the tag matches all snippets that contain the tag are fetched using jsonPath:



JsonPath.read(snippetManager.getJsonObj(),

​                "$.snippets[?('" + tag + "' in @.tags " + additionalConstraint + ")]");



If the snippet json entry is not in the search results list already, that is passed from *getSearchResults*, it is added. The *performTagSearch* function returns to *getSearchResults*.



The searchResults are converted to a list of **Snippet** objects and this list is returned.

# Implementation done for Displaying:

Displaying is implemented with the help of the observer pattern. The controllers that are responsible for the displaying of the snippet data, are BaseController and FullSnippetController. The information that is displayed on their gui elements needs to be reloaded after every change; this situation is explained in the Application of design patterns section. In this section a closer look is given to the *loadDisplayData* function in the **BaseController.** This function is called in the initialize function to display the default state. It is also called when the “favourites” button or the “All” button is pressed. The callback method executes it as well. The search bar calls it every time a key is released to constantly refresh the display with the search results. The final element that triggers the execution of this method is the choice box. Whenever a new sorting order is picked the listview is loaded again. 

The function works as follows: it first checks whether or not the search bar was typed in if this is the case only snippets that match the search from the search bar should be fetched. If the search bar is empty the whole collection can be fetched. Collections are fetched based on the state's DisplayFavourites or DisplayAll. After fetching the collection, it is passed to the sortDisplaySnippets function to be sorted on the picked choicebox option. Finally the listview is reloaded.

# 

# References

[1] M. P. Robillard, *Introduction to Software Design with Java,* Springer Nature Switzerland, 2019

[2] E. Freeman, E. Freeman, K. Sierra, B. Bates, *Head First Design Patterns*, O’Reilly Media, 2004