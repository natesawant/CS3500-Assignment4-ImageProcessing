# Image Processing Program
## For CS3500 - Object Oriented Design
### By Nathaniel Sawant and Aiden Cahill

## Contents
1. [About](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#about)
2. [Design](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#design)
   - [Controllers](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#controllers)
   - [Models](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#models)
   - [Views](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#views)
   - [Utils](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#utils)
4. [Instructions](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#instructions)
   - [Running The Program](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#running-the-program)
   - [Valid Commands](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#valid-commands)



## About

This project is a basic image processing program. Currently, the program is only able to work with P3 .ppm files, an ASCII based image file. The program is capable of loading and saving the files as this format, however, it will likely support other files in the future. The program can perform basic image manipulations, such as flipping the image, setting the image to greyscale, and applying kernels. 

## Design

### Controllers

#### Interfaces

1. ImageProcessingController
   1. Provides the initializeProgram() method, which serves as a starting point to the program.

#### Classes

1. ImageProcessingControllerImplementation | implements ImageProcessingController
   1. Allows the user to interact with the program, sending inputted commands to the model, and 
      outputting the results to the view.
2. Image Processing
   1. Provides a main(String[] args) method to serve as the entry point to the program.

### Models

#### Interfaces

1. Image
   1. Represents a general image, provides method that allow for the obtainment of specific pixels,
      the width, height, and max value of any color in an image.
2. OperationsModel
   1. Provides various operations that can be used to load, modify, and save images. The full
      details of each method is detailed below.
   2. Operations available:
      1. Load : loads a specified image into the program's filesystem
      2. Save : saves a modified image into the program's filesystem
      3. ApplyKernel : applies a custom kernel matrix to an image
      4. BoxBlur : performs a box blur of a specified radius on an image
      5. Brighten : adjusts the brightness of an image by a given delta
         1. Negative values will darken the image
      6. Emboss : embosses the specified image
      7. GaussianBlur : performs a gaussian blur on a specified image
      8. HorizontalFlip : flips the specified image horizontally
      9. VerticalFlip : flips the specified image vertically
      10. RidgeDetection : produces an image consisting of all edges in the given image
      11. Sharpen : adds a sharpening filter on a specified image
      12. ValueComponent : creates an image based on one of the following components of each pixel:
          1. Red-pixel value
          2. Green-pixel value
          3. Blue-pixel value
          4. The strongest color value in each pixel
          5. The luma value of each pixel
          6. The intensity of each pixel
   

#### Classes

1. AbstractImage | implements Image
   1. Stores fields that any image should contain, regardless of type.
2. RGBImage | extends AbstractImage
   1. Represents a slightly more specific image, where each pixel is made up of an RGB value.
3. OperationsModelManager | implements OperationsModel
   1. Contains the implementation and execution of the different operations and modifications that 
      can be used on an Image.

### Views

#### Interfaces

1. ImageProcessingView
   1. Represents a general text view that can render String messages.

#### Classes

1. ImageProcessingTextView | implements ImageProcessingView

### Utils

#### Interfaces

#### Classes

1. ImageUtil
   1. Allows for the conversion and reading of .ppm image files in the program's current filesystem.

## Instructions

### Running The Program
#### To compile, open terminal or the commandline in the _src/_ directory. To do so, run the following: `javac controller/ImageProcessing.java`. To run the program to allow free image processing, start it by running the following: `java controller/ImageProcessing`. This takes in the optional argument of the working directory, with the slash at the end.
`java src/controller/ImageProcessing.java` or `java src/controller/ImageProcessing.java images/`

### Script To Apply All Processes
#### The file `ApplyAllScripts.java` takes in the _directory_ without the slash at the end and the _filename_ with the .ppm extension. It then applies all the available processes to the image and saves them with the process at the end in the same directory.
`java src/controller/ApplyAllScripts.java directory filename` or `java src/controller/ApplyAllScripts.java filename`

###### Example:
> `ApplyAllScripts.java images igm`

### Valid Commands

1. [Load](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#load)
2. [Save](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#save)
3. [Brighten](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#brighten)
4. [Vertical Flip](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#vertical-flip)
5. [Horizontal Flip](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#horizontal-flip)
6. [Value Component](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#value-component)
7. [Box Blur](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#box-blur)
8. [Gaussian Blur](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#gaussian-blur)
9. [Emboss](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#emboss)
10. [Sharpen](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#sharpen)
11. [Ridge Detection](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#ridge-detection)
13. [Quit](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#quitting-the-program)

and more to come!

_Disclaimer: This photo was taken by Nathaniel Sawant and is authorized for use in this project._

#### Load
##### Loads an image from _filepath_ into memory, which can be accessed with _name_.
`load filename name`

###### Example:
> `load images/igm.ppm igm`

![igm](https://user-images.githubusercontent.com/74106957/172696652-999fc1ba-6d71-4948-8082-3a59105cb115.jpg)

#### Save
##### Saves an image to _filepath_ from the loaded image _name_.
`save filename name`

###### Example:
> `save images/igm-copy.ppm igm`

![igm-copy](https://user-images.githubusercontent.com/74106957/172696750-3896fffb-df8e-42a7-a8da-9114166c8fad.jpg)

#### Brighten
#### Brightens (or darkens if negative) the image _name_ by an _increment_ and stores it as _destName_.
`brighten increment name destName`

###### Examples:
> `brighten 100 igm igm-bright`

![igm-bright](https://user-images.githubusercontent.com/74106957/172696781-9da33406-1373-4019-b9b8-2e13a0e20a03.jpg)


> `brighten -100 igm igm-dark`

 ![igm-dark](https://user-images.githubusercontent.com/74106957/172697312-55dfbd30-c2d9-43ce-bf1c-951ed5239f57.jpg)

#### Vertical Flip
#### Flips the image _name_ vertically and stores it as _destName_.
`vertical-flip name destName`

###### Example:
> `vertical-flip igm igm-vertical`

![igm-vertical](https://user-images.githubusercontent.com/74106957/172696858-5e6f175a-f975-4a68-8384-80517227333f.jpg)

#### Horizontal Flip
#### Flips the image _name_ horizontally and stores it as _destName_.
`horizontal-flip name destName`

###### Example:
> `horizontal-flip igm igm-horizontal`

![igm-horizontal](https://user-images.githubusercontent.com/74106957/172696916-77377dca-8712-4dc1-ba05-262d996419d4.jpg)

#### Value Component
#### Isolates the _component_ (red, green, blue, value, luma, intensity) of the image _name_ and stores it as _destName_.
`value-component component name destName`

###### Examples:
> `value-component red igm igm-red`

![igmred](https://user-images.githubusercontent.com/74106957/172696985-f3d0ad02-3016-4b71-9a85-ac0d3966051a.jpg)


> `value-component green igm igm-green`

![igmgreen](https://user-images.githubusercontent.com/74106957/172697551-9f2a91c2-e785-4695-8b19-e5325671c1cd.jpg)


> `value-component blue igm igm-blue`

![igmblue](https://user-images.githubusercontent.com/74106957/172697579-fe1d7b16-1288-40bd-8f71-57c52905c459.jpg)


> `value-component luma igm igm-luma`

![igmluma](https://user-images.githubusercontent.com/74106957/172697594-ff1192d0-a7e2-4394-a5d5-2e0756a8e0e5.jpg)


> `value-component luma igm igm-intensity`

![igmintensity](https://user-images.githubusercontent.com/74106957/172697622-d458f8d9-c68e-4b7e-a140-ed2de4376466.jpg)


> `value-component luma igm igm-value`

![igmvalue](https://user-images.githubusercontent.com/74106957/172697654-7c771bf8-864c-4556-a798-6cda2294bca1.jpg)

#### Box Blur
#### Blurs all the adjacent pixels of the image _name_ in a square method and stores it as _destName_.
`box-blur name destName`

Box Blur Kernel (3x3)
| 1/9 	| 1/9 	| 1/9 	|
|-----	|-----	|-----	|
| 1/9 	| 1/9 	| 1/9 	|
| 1/9 	| 1/9 	| 1/9 	|

###### Examples:

> `box-blur igm igm-boxblur`

![igmblur](https://user-images.githubusercontent.com/74106957/172703956-c6cf1751-5a91-428a-96af-587a866e6a48.jpg)

#### Gaussian Blur
#### Blurs all the adjacent pixels of the image _name_ in a circular method and stores it as _destName_.
`gaussian-blur name destName`

Gaussian Blur Kernel (3x3)
| 1/16	| 2/16	| 1/16	|
|-----	|-----	|-----	|
| 2/16	| 4/16	| 2/16	|
| 1/16	| 2/16	| 1/16	|

###### Examples:

> `gaussian-blur igm igm-gaussianblur`

![igmgaussian](https://user-images.githubusercontent.com/74106957/172895502-c59e2b8b-0f73-4d80-9d82-3bf2029f3dd7.jpg)

#### Emboss
#### Appears to raise the pixels of the image _name_ by emphasizing contrast and stores it as _destName_.
`emboss name destName`

Emboss Kernel (3x3)
| -2 	| -1 	| 0  	|
|----	|----	|----	|
| -1 	|  1 	|  1 	|
| 0  	|  1 	|  2 	|

###### Examples:

> `emboss igm igm-emboss`

![igmemboss](https://user-images.githubusercontent.com/74106957/172895470-cabaea02-0538-41b7-9e70-3505ba98a9c1.jpg)

#### Sharpen
#### Sharpens the pixels of the image _name_ by emphasizing contrast and stores it as _destName_.
`sharpen name destName`

Sharpen Kernel (3x3)
| 0  	| -1 	| 0  	|
|----	|----	|----	|
| -1 	| 8  	| -1 	|
| 0  	| -1 	| 0  	|

###### Examples:

> `sharpen igm igm-sharp`

![igmsharp](https://user-images.githubusercontent.com/74106957/172895429-137dfc78-c0e7-45c8-ae39-159174b5182e.jpg)

#### Ridge Detection
#### Detects the edges (high contrast) of the image _name_ and stores it as _destName_.
`ridge-detection name destName`

Ridge Detection Kernel (3x3)
| -1 	| -1 	| -1 	|
|----	|----	|----	|
| -1 	| 8  	| -1 	|
| -1 	| -1 	| -1 	|

###### Examples:

> `ridge-detection igm igm-ridge`

![igmridge](https://user-images.githubusercontent.com/74106957/172895394-b63f6578-dcbe-414d-91a5-79fc8267f87f.jpg)

#### Quitting the program
#### Terminates the program. Any unsaved work will be lost. Cannot be done whilst in the middle of inputting a command.

`q` or `quit`
