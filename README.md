# Image Processing Program
## For CS3500 - Object Oriented Design
### By Nathaniel Sawant and Aiden Cahill

## What is it?

## Design

### Controllers

### Models

### Views

### Utils

## How To Use

### Valid Commands
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

###### Example:
> `brighten 10 igm igm-bright`

![igm-bright](https://user-images.githubusercontent.com/74106957/172696781-9da33406-1373-4019-b9b8-2e13a0e20a03.jpg)
 
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

###### Example:
> `value-component luma igm igm-luma`

![igmred](https://user-images.githubusercontent.com/74106957/172696985-f3d0ad02-3016-4b71-9a85-ac0d3966051a.jpg)

#### Box Blur
