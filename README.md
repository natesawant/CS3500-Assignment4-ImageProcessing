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
> `load images/koala.ppm koala`

#### Save
##### Saves an image to _filepath_ from the loaded image _name_.
`save filename name`

###### Example:
> `save images/koala-copy.ppm koala`

#### Brighten
#### Brightens (or darkens if negative) the image _name_ by an _increment_ and stores it as _destName_.
`brighten increment name destName`

###### Example:
> `brighten 10 koala-bright`
 
#### Vertical Flip
#### Flips the image _name_ vertically and stores it as _destName_.
`vertical-flip name destName`

###### Example:
> `vertical-flip koala-vertical`

#### Horizontal Flip
#### Flips the image _name_ horizontally and stores it as _destName_.
`horizontal-flip name destName`

###### Example:
> `horizontal-flip koala kaoal-horizontal`

#### Value Component
#### Isolates the _component_ (red, green, blue, value, luma, intensity) of the image _name_ and stores it as _destName_.
`value-component component name destName`

###### Example:
> `value-component luma koala koala-luma`

#### Box Blur
