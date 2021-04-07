# Test double example

This is a very simple example to show test doubles.

- Stub
- Spy
- Mock

Using Junit 5.0 and Mockito 3.8.0.

## Scenarios

Chef wants to put vegetables to the Fridge, return true if successed otherwise return false.

Fridge can store objects but it will throw exception if it is full.

SUT - `Chef`

DOC - `Fridge`

### Scenario 1

If we only care whether the chef can successfully put the vegetable into the fridge.

```
Given fridge is not full,  
when chef put vegetable into fridge,   
then return true.  
```
```
Given fridge is full,
when chef put vegetable into fridge,
then return false.
```

### Scenario 2

If we care the capacity changes of the fridge.

```
Given fridge is not full,  
when chef put vegetable into fridge,   
then the capacity of the fridge will decrease.
```

```
Given fridge is full,  
when chef put vegetable into fridge,   
then the capacity of the fridge will not change.
```

### Scenario 3

If we care whether the action call happens.

```
Given fridge is not full,  
when chef put vegetable into fridge,   
then return true,
and he closed the door.
```

```
Given fridge is full,  
when chef put vegetable into fridge,   
then return false,
and the closed the door.
```
