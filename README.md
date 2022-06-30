# Movies
An Android app am using [The TMDB API ](https://developers.themoviedb.org/3/getting-started/introduction) to display Movies. Am building it with with clean architecture principles, Repository and MVVM with JetPack Compose Libraries.
Its a work in progress trying to implement all the related libraries on Android using Kotlin and working with the best architecture.

## Project Characteristics
The project uses best practices, tools and solutions: 
  * Kotlin
  * MVVM architecture.
  * Dependency Injection

## Tech Stack
The Libraries am using in the Development of the whole Application.

  * Architecture
    
      * MVVM - application level
      * Android Architecture components

  * Stack

    * [Jetpack](https://developer.android.com/jetpack)🚀 - libraries that help follow best practices, reduce boilerplate code, and write code that works consistently across Android versions and devices so that developers can focus on the code they care about.
    * [Dagger-Hilt](https://dagger.dev/hilt/) - For dependency injection
    * [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - support library that allows binding of UI components in layouts to data sources,binds character details and search results to UI.
    * [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started)
    * [Moshi](https://github.com/square/moshi) - a modern JSON library for Android, Java and Kotlin. It makes it easy to parse JSON
    * [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) - library helps you load and display pages of data from a larger dataset from local storage or over network.
    * [Coroutines](https://developer.android.com/kotlin/coroutines?gclid=CjwKCAjwk_WVBhBZEiwAUHQCmdx8rjojm7dxpQ2EGOYQydzDN3DbqnzZBC0nq-GGzvdmCvnnFYvgFRoCyPEQAvD_BwE&gclsrc=aw.ds) - is a concurrency design pattern that you can use on Android to simplify code that executes asynchronously
    * [Flow](https://developer.android.com/kotlin/flow) - is a type that can emit multiple values sequentially, as opposed to suspend functions that return only a single value.
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel?gclid=CjwKCAjwjJmIBhA4EiwAQdCbxrvUiq3wgakPX8sop8Kp8irusL4bi_9xCnaiZkUJqBzTbOTB2FB4XRoCujoQAvD_BwE&gclsrc=aw.ds) - Manage UI related data in a lifecycle conscious way and act as a channel between use cases and ui

## Getting Started
  ### Android Studio/Command line
  1. Android Studio -> File -> New -> From Version control -> Git
  2. From the terminal run git clone https://github.com/Danc-0/Movies.git
  
  ### Running
    Create an account in [The TMDB API ](https://developers.themoviedb.org/3/getting-started/introduction) and request
    for  an APIKEY from which go to local.properties file in the build scripts and add it 
    as APIKEY = "YOURAPIKEY"


## Screenshots
This is a short demo of how the application looks like

|<img src="images/firstscreen.jpg" width=200/>|<img src="images/secondscreen.jpg" width=200/>|<img src="images/thirdscreen.jpg" width=200/>|<img src="images/fourthscreen.jpg" width=200/>|
|:----:|:----:|:----:|:----:|

|<img src="images/fifthscreen.jpg" width=200/>|
|:----:|

## Contributions

Contibutions are welcomed. Feel free to add anything or remove anything that is unnecessary

## Support

Give a ⭐ if you like the Project
