# Solitaire
KP Fellows Solitaire Project

# Instructions 

To run simply clone or download the repo and run 

``` 
java -jar Solitaire.jar
```

If your cards have a `?` on them you may want to run in `easy-read` mode by running 

``` 
java -jar Solitaire.jar
```

# Project Structure and Design Pattern 

The solitaire game uses an MVP design pattern. The main driver (view) class, `Driver/Solitaire.kt` displays all the elements to the screen. 
It also handles all input to the game. 

Once the driver recieves input it notifies the presenter (`GUI/BoardPresenter`) of the user input. 
The boardPresenter can then verify the input, and pass on the information to each model. If the user input is invalid, it notifies the 
view and the view can handle displaying an error. 

The models store each of the structures data - there's the foundations, cards, columns, decks, and rank/suit. These are almost pure data
objects and do not have extensive functionality other than ways to add/remove and view the data. 

There is also one last package - utils. Utils has the CardDisplayHelper, which helps each model convert into an appropriate display. 


# Why Kotlin

I love Kotlin! I've been using it for Android, and love some of the features it adds - easy getters/setters, data objects, tuples, and better constructors 
are all features I really wanted to use. It also often feels cleaner than the same Java code I would write (which makes readability and improvement much easier)

