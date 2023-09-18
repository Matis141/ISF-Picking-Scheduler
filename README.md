# Ocado internship recruitment task

Program allows to schedule picking tasks for ISF workers so that most tasks will be done.

## Description

Algorithm sorts order list based on order's complete by and picking time.
Search for tasks is done by loop that continues until order list is empty.
During loop picker with lowest starting time - time of picking start time plus all of already done orders durations.
After finding such picker then algorithm tries to assign this task to this picker.
Assigning is done by comparing order's complete by time and picker's current time to see if he will make it on time.
Additionally it also checks if this time does not surpassess shop's closing time.
Whether it is possible to assign this task or not, it gets deleted from searching list and then search continues on modified order list.
If picker gets assigned an order to complete, it's time is used to calculate future time on next loop runs.
This may not be optimal algorithm, although it provides approximate results in low time.
JSON data is being read by using Jackson library.
Build automation tool used: Maven.

### Executing program

Java 17 is required to run program.
In order to run user has to use following command:
java -jar /home/…/app.jar /home/…/store.json /home/…/orders.json
where app.jar is the location of app, store.json and orders.json are corresponding JSON data files required to run algorithm.
App.jar is located in target directory.
Result is displayed on standard output in such manner:
picker-id order-id picking-start-time
It means that such picker has to start working on such order at given time.


#### Authors

Mateusz Auguścik

