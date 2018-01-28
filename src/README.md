# Interpreter
First group led project at Makers Academy, we where given four and a half days to do as much as we could. We initially decided to write a programming language, however soon we realised that in order to so, we would have to write a compiler or interpreter. We went for the simpler option of the interpreter. By the end of the week, we where able to add elements of our own language into the interpreter. We chose to write the interpreter in pure Java as the week presented itself as an opportunity to learn a new language at the same time as writing a project.

## Getting started
* First off, you will need to have the Java Virtual Machine (JVM) installed on your computer. To do this follow the set up instructions [here](https://java.com/en/download/).

* Once java is downloaded, download this repo.

* Open terminal and navigate to the repo, then into the src folder.

* To run the programme, type
```
java Main
```
into the terminal.

* If the programme runs, you should see the following. This is waiting for your input.
```
MOM>>
```

### Syntax
the interpreter can do basic mathematics, variable assignment, print variables, and follows a block structure.
For every input, you start by inputing 'please', and to finish every input input 'thanks.'. The print command is 'say', and variable assignment is done with '='.

##### example one
```
MOM>> please
MOM>> a = -(1+(5*3))/2;
MOM>> say a
MOM>> thanks.
-8
```
As you can see, the output is -8, which is not assigned to a. The interpreter understands order of operations, ignores unnecessary whitespace as well as brackets. You don't have to input on separate lines, however it is suggested for readability.
If we want to reuse our variable in the next calculation, we can

##### example two
```
MOM>> please
MOM>> say a + 1 *    2  
MOM>> thanks.
-16
```
Here we can see that we reused the pre defined variable a.

To quit, use
```
MOM>> quit
```


## Tests
To run, change into spec, and run TestRunner.
```
cd spec
```
```
java TestRunner
```
Tests cover the three parts of the Interpreter, that is the Lexer, the Parser and finally the Interpreter.

## Authors
* [Tom Allpress](https://github.com/tallpress)
* [Umair Bashir](https://github.com/umairb1)
* [Lars Finlay](https://github.com/LarsFin)
* [James Lizamore](https://github.com/Meepit)
* [George Whiting](https://github.com/GeorgeWhiting)
