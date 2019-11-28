ASHWINI KUMAR
ASSIGNMENT 5
COL 106

In the assignment 5, we were asked to implement three data structures namely priority queue, Red-Black Tree.
There are 3 packages for each of them and 1 seperate package for PROJECT MANAGEMENT.

PACKAGE PRIORITY QUEUE
Class Student: An object of this class represents a particular student. It contains all the information about
the student such as name, marks,etc. It implements Comparable<Student> and thus overrides the compareTo() 
function.

Class MaxHeap: An object of this type represents the data structure Maximum Heap. It can be represented by 
an array(heap array). It contains three functinos:
insert(): inserts the given object of Student(or Job) class based on their priority 
ExtractMax(): This removes the highest priority element in the Maximum heap and rearranges the rest of the 
heap. Remove the first element of the heap array and replace it with the last element in the array. Further,
replace that element with its childs based onn their priorities.
getList(): Returns the heap array.

PriorityQueueDriverCode: This calls an object of the class MaxHeap and inserts and extracts the maximum 
priority element based. A file is read in the main function of this class and based on the input file
various functions are called.

Average Case:
INSERT: O(logN)
DELETE: O(logN)

Worst Case:
INSERT: O(logN)
DELETE: O(logN)

PACKAGE TRIE:
Class Person: An object of this class represents a particular person. It contains functions such as getName(),
getValue() which returns name, Contact No. respectively. 
Class TrieNode: An object of this class represents a node in the data structure Trie. It contains an array 
which represents its child.
Class Trie: An object of this class represents the data structure Trie. It contains several functions such as
delete, insert, search, startsWith,etc.

M=Length of the string

Average Case:
INSERT: O(M)
DELETE: O(M)
SEARCH: O(M)
STARTSWITH: O(M)
PRINTTRIE: O(M)
PRINT: O(M)
PRINTLEVEL: O(M)

Worst Case:
INSERT: O(M)
DELETE: O(M)
SEARCH: O(M)
STARTSWITH: O(M)
PRINTTRIE: O(M)
PRINT: O(M)
PRINTLEVEL: O(M)

PACKAGE REDBLACK
Class RedBlackNode: An object of this class contains a node of RedBlack Tree. It contains various information
about the node such as key, value(s),left child node, right child node, colour. 
Class RedBlackTree: An object of this class represents the data structure RedBlack Tree. It contains various
nodes i.e. objects of heap Node. It mainly contains insert() and search() functions.

N=Number of total nodes in tree
Average Case:
INSERT: O(logN)
SEARCH: O(logN)

Worst Case:
INSERT: O(logN)
SEARCH: O(logN)

PACKAGE PROJECT MANAGEMENT
Class Job: An object of this class represents a particular job. It has several information about the job such 
as name of job, project, user, arrival time, completion time, etc. It contains various functions such as
compareTo(), getRunTime(), getPriority(), getJobName(), etc.It implements JobReport_ .

Class Project: An object of this class represents a particular project. It contains information 
about the project such as project name, budget, priority. 

Class User: An object of this class represents a particular user. It contains information about the user such
as name, its latest job and the time consumed by the jobs of this user.It implements UserReport_ .

Class Scheduler_Driver: It reads a file and takes input from it. Further it answers the queries as mentioned
below: 
handle_project(): Creates an object of class Project and stores that object in the projects trie.
handle_job(): Creates an object of class Job and stores in jobs(MaxHeap).
handle_user(): Creates an object of class User and stores it in users(Trie) 
handle_empty_line(): A cycle is executed till a job is successfully executed. It searches jobs from the jobs
(MaxHeap) and if it has sufficient budget then runs that job else keeps in a seperate array list.
handles_query(): Returns whether the job is finished or not.
handle_add(): It searches the project in projects(Trie) and then increases its budget by the given amount.
If there was any job which cannot get succesfully executed due to insufficient budget, this function also 
takes cares of these jobs.
run_to_completion(): Executes all the remaining jobs based on their budgets. Similarly, adds the job of unsufficient
budget to a seperate list and completed jobs to another array list.
print_stats(): Prints all the jobs in the database whether they were unfinished or finished with their 
information(statistics).
timed_handle_user(): prints the time in which a user object is created.
timed_handle_job(): prints the time in which a job object is created.
timed_handle_project(): prints the time in which a project object is created.
timed_run_to_completion(): prints the time taken in the running of run_to_completion() function. 

ArrayList<JobReport_> timed_report: Returns the list of all jobs with the given project and having arrival time in between given 
intervals/ jobs with the given user and having arrival time in between given intervals/ jobs with the given project and given user
having arrival time in between given intervals/ jobs having enough budgets and priorities greater than or 
equal to the given integer.

ArrayList<UserReport_>timed_top_consumer: Returns the list of top users arranged from higher to lower 
consuming times.

timed_flush(): Prioritise the longer waiting jobs to execute them irrespective of the empty line command.
Temporarily increase the priority of that job to be highest and extract it from the heap if it has 
sufficient budget.Execute it if they have waiting time greater than or equal to the given time
and increase the GLOBAL TIME by the runtime of the executed job.