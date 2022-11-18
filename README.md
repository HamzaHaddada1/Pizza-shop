# Pizza-shop

Architecture: 
I have used an event driven architecture to implement the task because we want to truck the state of the delivery base on
events (OrderCreated, OrderCanceled and orderDelivered) and I have chosen CQRS because separating write from read activity 
leads to more efficient scaling of storage capacity based on real-world usage. I have used also Event sourcing to create a log of events.

Implementation:
Since it's an event-sourced java application I preferred to use axon https://www.axoniq.io/ because it provides a good tools
for faster implementation.
H2 database for light weight in memory database.

For the implementation we have two entry points wich are the commands for writing and the query for reading in this task
I have used the REST protocol.

Compromises:
1 -If I have more time I would spend more time to write exception, I would add a customized exception class in the lib package(something like BusinessException)
to give more appropriate exception messages.

2- I would add an appropriate relation between Order, Item, Toppings class in the query and not save it as String.

Running the System:
please run this command to run axon server `docker run -d --name axonserver -p 8024:8024 -p 8214:8124 axoniq/axonserver`
build the project `docker build -t challenge .`
run the project `docker run -i -t -p 8080:8080 challenge`