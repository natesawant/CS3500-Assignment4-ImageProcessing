#Image Processing Program USEME
### Aiden Cahill and Nate Sawant

## How to run via .jar file
To run this program via the jar file, first navigate to the res folder, then run the command:
`java Assignment4-ImageProcessing.jar [optional specified directory] -file [optional specified .txt file providing desired commands]`
Options:
-text : runs the text version of the program
-file [file] : runs the program based off of a file script
No options will launch the GUI

### Examples:
`java Assignment4-ImageProcessing.jar /images`
`java Assignment4-ImageProcessing.jar -file script.txt`
`java Assignment4-ImageProcessing.jar /res -file script.txt`
`java Assignment4-ImageProcessing.jar -text`

## Available operations:
Note: Before you are able to use any of the following operations on an image, you must first
    load an image into the program using the following command:
       `load [filename] [name]`
        where `[filename]` is the location of the image to be loaded, and `[name]` is name that
        the image will be referred to in the program.`


1. General Commands
    - [Load](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#load)
    - [Save](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#save)
    - [Quit](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#quitting-the-program)
2. Image Transforms
    - [Horizontal Flip](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#horizontal-flip)
    - [Vertical Flip](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#vertical-flip)
    - [Downscale]
3. Color Filters
    - [Adjust Blue](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#adjust-blue)
    - [Adjust Green](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#adjust-green)
    - [Adjust Red](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#adjust-red)
    - [Brighten](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#brighten)
    - [Invert Colors](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#invert-colors)
    - [Sepia Tone](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#sepia-tone)
    - [Value Component](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#value-component)
4. Image Filters
    - [Box Blur](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#box-blur)
    - [Emboss](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#emboss)
    - [Gaussian Blur](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#gaussian-blur)
    - [Sharpen](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#sharpen)
    - [Ridge Detection](https://github.com/natesawant/CS3500-Assignment4-ImageProcessing#ridge-detection)

and more to come!

_Disclaimer: This photo was taken by Nathaniel Sawant and is authorized for use in this project._

#### **General Commands**

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

#### Quitting the program
#### Terminates the program. Any unsaved work will be lost. Cannot be done whilst in the middle of inputting a command.

`q` or `quit`

#### **Image Transforms**

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

#### Downscale
#### Downscales the image _name_ to a desired _width_ and _height_
`downscale width height name destName`

###### Example:
> `downscale 100 100 igm igm-downscale`

#### **Color Filters**

#### Adjust Blue
#### Increase (or decreases if negative) the blue channel of the image _name_ by an _increment_ and stores it as _destName_.
`adjust-blue increment name destName`

Adjust Blue Filter (3x1)
| 0	|
|----	|
| 0	|
| i	|

###### Examples:
> `adjust-blue 100 igm igm-more-blue`

![igm-more-blue](https://user-images.githubusercontent.com/74106957/173624563-543c8937-7d39-45f6-9cfd-40302bab3a84.jpg)

> `adjust-blue -100 igm igm-less-blue`

![igm-less-blue](https://user-images.githubusercontent.com/74106957/173624621-5ee5cec6-828b-4f7d-b8fa-6701f1b6d440.jpg)

#### Adjust Green
#### Increase (or decreases if negative) the green channel of the image _name_ by an _increment_ and stores it as _destName_.
`adjust-green increment name destName`

Adjust Green Filter (3x1)
| 0	|
|----	|
| i	|
| 0	|

###### Examples:
> `adjust-green 100 igm igm-more-green`

![igm-more-green](https://user-images.githubusercontent.com/74106957/173624997-3236bfae-de72-4c25-963b-8d7308de4f3b.jpg)

> `adjust-green -100 igm igm-less-green`

![igm-less-green](https://user-images.githubusercontent.com/74106957/173624973-d692817b-69ec-4872-a0cf-26783655c04d.jpg)

#### Adjust Red
#### Increase (or decreases if negative) the red channel of the image _name_ by an _increment_ and stores it as _destName_.
`adjust-red increment name destName`

Adjust Red Filter (3x1)
| i	|
|----	|
| 0	|
| 0	|

###### Examples:
> `adjust-red 100 igm igm-more-red`

![igm-more-red](https://user-images.githubusercontent.com/74106957/173625098-be10e458-7a72-4f94-a15c-495e7451262e.jpg)

> `adjust-red -100 igm igm-less-red`

![igm-less-red](https://user-images.githubusercontent.com/74106957/173625114-eac5c6e7-0201-4c4b-8adf-3479795edab6.jpg)

#### Brighten
#### Brightens (or darkens if negative) the image _name_ by an _increment_ and stores it as _destName_.
`brighten increment name destName`

Brightness Filter (3x1)
| i	|
|----	|
| i	|
| i	|

###### Examples:
> `brighten 100 igm igm-bright`

![igm-bright](https://user-images.githubusercontent.com/74106957/172696781-9da33406-1373-4019-b9b8-2e13a0e20a03.jpg)


> `brighten -100 igm igm-dark`

![igm-dark](https://user-images.githubusercontent.com/74106957/172697312-55dfbd30-c2d9-43ce-bf1c-951ed5239f57.jpg)

#### Invert Colors
#### Inverts the colors of the image _name_ and stores it as _destName_.
`invert name destName`

Invert Filter (3x3)
| (255-r)/r	| 0	| 0	|
|-----	|-----	|-----	|
| 0	| (255-g)/g	| 0	|
| 0	| 0	| (255-b)/g	|

###### Examples:
> `invert igm igm-invert`

![igminverted](https://user-images.githubusercontent.com/74106957/173146965-570df912-c6d5-4979-8cb3-d6cfdb35ba92.png)

#### Sepia Tone
#### Creates a characteristic reddish brown tone to image _name_ and stores it as _destName_.
`sepia-tone name destName`

###### Examples:
> `sepia-tone igm igm-sepia`

Sepia Tone Filter (3x3)
| 0.393	| 0.769	| 0.189	|
|-----	|-----	|-----	|
| 0.349	| 0.686	| 0.168	|
| 0.272	| 0.534	| 0.131	|

![igmsepia](https://user-images.githubusercontent.com/74106957/173144921-bf0f3649-e5d1-497c-a41c-f32093d44b4a.png)

#### Value Component
#### Isolates the _component_ (red, green, blue, value, luma, intensity) of the image _name_ and stores it as _destName_.
`value-component component name destName`

###### Examples:
> `value-component red igm igm-red`

Red Grayscale Filter (3x3)
| 1	| 0	| 0	|
|----	|----	|----	|
| 1	| 0	| 0	|
| 1	| 0	| 0	|

![igmred](https://user-images.githubusercontent.com/74106957/172696985-f3d0ad02-3016-4b71-9a85-ac0d3966051a.jpg)


> `value-component green igm igm-green`

Green Grayscale Filter (3x3)
| 0	| 1	| 0	|
|----	|----	|----	|
| 0	| 1	| 0	|
| 0	| 1	| 0	|

![igmgreen](https://user-images.githubusercontent.com/74106957/172697551-9f2a91c2-e785-4695-8b19-e5325671c1cd.jpg)


> `value-component blue igm igm-blue`

Blue Grayscale Filter (3x3)
| 0	| 0	| 1	|
|----	|----	|----	|
| 0	| 0	| 1	|
| 0	| 0	| 1	|

![igmblue](https://user-images.githubusercontent.com/74106957/172697579-fe1d7b16-1288-40bd-8f71-57c52905c459.jpg)


> `value-component luma igm igm-luma`

Luma Grayscale Filter (3x3)
| 0.2126	| 0.7152	| 0.0722	|
|-----	|-----	|-----	|
| 0.2126	| 0.7152	| 0.0722	|
| 0.2126	| 0.7152	| 0.0722	|

![igmluma](https://user-images.githubusercontent.com/74106957/172697594-ff1192d0-a7e2-4394-a5d5-2e0756a8e0e5.jpg)


> `value-component intensity igm igm-intensity`

Intensity Grayscale Filter (3x3)
| 1/3	| 1/3	| 1/3	|
|----	|----	|----	|
| 1/3	| 1/3	| 1/3	|
| 1/3	| 1/3	| 1/3	|

![igmintensity](https://user-images.githubusercontent.com/74106957/172697622-d458f8d9-c68e-4b7e-a140-ed2de4376466.jpg)


> `value-component value igm igm-value`

Value Grayscale Filter (3x1)
| max(r,g,b) - r	|
|--------------	|
| max(r,g,b) - g	|
| max(r,g,b) - g	|

![igmvalue](https://user-images.githubusercontent.com/74106957/172697654-7c771bf8-864c-4556-a798-6cda2294bca1.jpg)

#### **Image Filters**

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

#GUI - How to use

## A color-value histogram of the current image is shown on the right side of the screen

## File tab
Available operations:
Open - opens a specified image file
Save - saves the changes to the current image
Save As - saves the changes to a new image

##Processes Tab
Sub tabs:
- Image Transforms:
-   Flip the image horizontally or vertically
-   Downscale the image to specified dimensions that conform to the following boundaries: [0 , width_of_image) and [0 , height_of_image)
- Color filters:
-   Adjust specific RGB values
-   Apply a sepia filter
-   Invert colors
-   Apply varying forms of greyscale filters
- Image filters:
-   Box blur effect
-   Gaussian blur effect
-   Emboss
-   Sharpen Ridge detection

