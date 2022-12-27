# Sample AEM project template

This is a project template for AEM-based applications. It is intended as read the external feed and show the MSM concept.

## Modules

The main parts of the template are:

* core: Java bundle containing all core functionality like as component-related Java code such as Model, beans and helper files.
* ui.apps: contains the /apps (and /etc) parts of the project, ie JS&CSS clientlibs, components(RSS Feed Component) and templates.
* ui.content: contains RSS Feed content using the RSS Feed components and MSM from the ui.apps
* ui.tests: Java bundle containing JUnit tests that are executed server-side. This bundle is not to be deployed onto production.
* ui.launcher: contains glue code that deploys the ui.tests bundle (and dependent bundles) to the server and triggers the remote JUnit execution
* ui.frontend: Did not use.

## How to build

To build all the modules run in the project root directory the following command with Maven 3:

    mvn clean install

If you have a running AEM instance you can build and package the whole project and deploy into AEM with

    mvn clean install -PautoInstallPackage

Or to deploy it to a publish instance, run

    mvn clean install -PautoInstallPackagePublish

Or alternatively

    mvn clean install -PautoInstallPackage -Daem.port=4503

Or to deploy only the bundle to the author, run

    mvn clean install -PautoInstallBundle

## Testing

There are three levels of testing contained in the project:

* unit test in core: this show-cases classic unit testing of the code contained in the bundle. configure JoCoCo To test, execute:

    mvn clean test
    
* JaCoCo configure for code coverage : 

    path : /myproject/core/target/site/jacoco/index.html
    

## Maven settings

The project comes with the auto-public repository configured. To setup the repository in your Maven settings, refer to:

    http://helpx.adobe.com/experience-manager/kb/SetUpTheAdobeMavenRepository.html

## AEM myproject site info : -
    http://localhost:4502/sites.html/content/myproject

## Below is RSS Feed component path
    http://localhost:4502/content/myproject/en/rss-feed-page.html

## MSM site structure with an experience fragment, below is path
    http://localhost:4502/editor.html/content/myproject/global/in_tl.html
    

    
