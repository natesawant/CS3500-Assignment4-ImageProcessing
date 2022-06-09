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

#### Classes

1. ImageProcessingControllerImplementation
2. Image Processing

### Models

#### Interfaces

1. Image
2. OperationsModel

#### Classes

1. AbstractImage
2. RGBImage
3. OperationsModelManager

### Views

#### Interfaces

1. ImageProcessingView

#### Classes

1. ImageProcessingTextView

### Utils

#### Interfaces

#### Classes

1. ImageUtil

## Instructions

### Running The Program

### Valid Commands

1. [Load](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#load)
2. [Save](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#save)
3. [Brighten](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#brighten)
4. [Vertical Flip](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#vertical-flip)
5. [Horizontal Flip](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#horizontal-flip)
6. [Value Component](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#value-component)
7. Box Blur
8. Gaussian Blur
9. Emboss
10. Sharpen
11. Ridge Detection
12. [Apply Kernel](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#apply-kernel)

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

#### Apply Kernel
#### Applies the custom _kernel_ (an odd dimensioned matrix that samples surrounding pixels) to image _name_ and stores it as _destName_.
`apply-kernel kernel name destName`

###### Examples:

> `apply-kernel blur igm igm-blur`

Box Blur Kernel (3x3)\
| 1/9 	| 1/9 	| 1/9 	|\
|-----	|-----	|-----	|\
| 1/9 	| 1/9 	| 1/9 	|\
| 1/9 	| 1/9 	| 1/9 	|

![igmblur](https://user-images.githubusercontent.com/74106957/172703956-c6cf1751-5a91-428a-96af-587a866e6a48.jpg)


> `apply-kernel sharpen igm igm-sharp`

Sharpen Kernel (3x3)\
| 0  	| -1 	| 0  	|\
|----	|----	|----	|\
| -1 	| 8  	| -1 	|\
| 0  	| -1 	| 0  	|

![igmsharp](https://user-images.githubusercontent.com/74106957/172704022-22e18136-cd6e-4c29-a14d-f0483abf90bd.jpg)
