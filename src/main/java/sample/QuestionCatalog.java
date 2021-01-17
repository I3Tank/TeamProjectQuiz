package sample;

import java.util.*;
import java.util.Collections;
import java.util.List;

/*
"counter" is used to iterate through the chosen questions.
"arrayLength" is used to create a new array for the practiseMode. Depends on the length of the different(topic) arrays, so the length equals to the number of
all questions of that topic. (team questions coming soon). Minimum of 16 questions needed in each topic. 15 + one questions to replace(joker2).
The format of our questions is: "question#rightAnswer#wrongAnswer1#wrongAnswer2#wrongAnswer3"
 */

public class QuestionCatalog {
    static int counter = 0;
    static int arrayLength = 0;
    static String[] shuffledQuestions = new String[16];

    private final String[] math = {
            "Wieviel ist 1+1?#2#3#5#1",
            "Wieviel ist 2+2?#4#12#7#8",
            "Wieviel ist 3+3?#6#5#9#1",
            "Wieviel ist 4+4?#8#1#12#6",
            "Wieviel ist 5+5?#10#15#12#11",
            "Wieviel ist 6+6?#12#15#16#11",
            "Wieviel ist 7+7?#14#15#12#11",
            "Wieviel ist 8+8?#16#15#12#11",
            "Wieviel ist 9+9?#18#12#17#16",
            "Wieviel ist 10+10?#20#15#12#11",
            "Wieviel ist 11+11?#22#15#12#11",
            "Wieviel ist 12+12?#24#15#12#11",
            "Wieviel ist 13+13?#26#15#12#11",
            "Wieviel ist 14+14?#28#15#12#11",
            "Wieviel ist 15+15?#30#15#12#11",
            "Wieviel ist 1*1?#1#15#12#11",
            "Wieviel ist 2*2?#4#15#12#11",
            "Wieviel ist 3*3?#9#15#12#11",
            "Wieviel ist 4*4?#16#15#12#11",
            "Wieviel ist 5*5?#25#15#12#11",
            "Wieviel ist 6*6?#36#37#40#2",
            "Wieviel ist 7*7?#49#50#44#47",
            "Wieviel ist 8*8?#64#60#54#80",
            "Wieviel ist 9*9?#81#79#91#82",
    };
    private final String[] team = {
            "Wieviel ist 6*6?#36#37#40#2",
            "Wieviel ist 7*7?#49#50#44#47",
            "Wieviel ist 8*8?#64#60#54#80",
            "Wieviel ist 9*9?#81#79#91#82",
            "Wieviel ist 6*6?#36#37#40#2",
            "Wieviel ist 7*7?#49#50#44#47",
            "Wieviel ist 8*8?#64#60#54#80",
            "Wieviel ist 9*9?#81#79#91#82",
            "Wieviel ist 6*6?#36#37#40#2",
            "Wieviel ist 7*7?#49#50#44#47",
            "Wieviel ist 8*8?#64#60#54#80",
            "Wieviel ist 9*9?#81#79#91#82",
            "Wieviel ist 6*6?#36#37#40#2",
            "Wieviel ist 7*7?#49#50#44#47",
            "Wieviel ist 8*8?#64#60#54#80",
            "Wieviel ist 9*9?#81#79#91#82",
    };
    private final String[] bsys = {
            "Which statement about pruefung1.test is true? Drwx---rwx 2 root hans 4096 Jan 13 2020 pruefung1.test#Hans is the owner of the group file#Users in group hans are allowed to read the file#hans is the owner of the file#the filetype is test",
            "Which statement about pruefung1.test is true? Drwx---rwx 2 root hans 4096 Jan 13 2020 pruefung1.test#Users which are not in group hans can execute the file#Users in group hans are allowed to read the file#hans is the owner of the file#the filetype is test",
            "Which statement is true? Operating systems#are managing hardware resources#are always monolithic#are running in user mode only#manage ressources via code-division multiplexing",
            "Which statement is true? Operating systems#provide system services for application programs#are always monolithic#are running in user mode only#manage ressources via code-division multiplexing",
            "Which statement is true? Operating systems#provide abstractions to application programmers#are always monolithic#are running in user mode only#manage ressources via code-division multiplexing",
            "By an operating System, the resource management can be done via#time division multiplexing#frequency division multiplexing#code-division multiplexing#digital multiplexing",
            "By an operating System, the resource management can be done via#space division multiplexing#frequency division multiplexing#code-division multiplexing#digital multiplexing",
            "Which of the following are examples for space multiplexed resources?#Memory (RAM)#Single Core CPU#Keyboard#Printer",
            "Which of the following are examples for space multiplexed resources?#Multi Core CPU#Single Core CPU#Keyboard#Printer",
            "Which of the following statements are true? In an interrupt driven output#The CPU receives an interrupt when new data has arrived and is ready to be retrieved by the processor#the process waiting for the I/O data remains in the CPU#the CPU runs a user written code and does accordingly#the CPU uses polling to watch the control bit constantly, looping to see if the device is ready",
            "In which ways can Input and Output be done?#Direct Memory Access (DMA)#Random Access#Digital Converter (DAC)#Random waiting",
            "In which ways can Input and Output be done?#Interrupt#Random Access#Digital Converter (DAC)#Random waiting",
            "In which ways can Input and Output be done?#Busy waiting#Random Access#Digital Converter (DAC)#Random waiting",
            "Which of the following is true for processes?#Possible process states are: ready, running, waiting#A process is part of a programm loaded into main memory#Possible process states are ready, running, interrupted#The program counter is shared by all threads in a process",
            "Which of the following is true for processes?#A process is a program in execution #A process is part of a programm loaded into main memory#Possible process states are ready, running, interrupted#The program counter is shared by all threads in a process",
            "Which of the following is true for processes?#A process is associated with an address space in main memory #A process is part of a programm loaded into main memory#Possible process states are_ ready, running, interrupted#The program counter is shared by all threads in a process",
            "Which of the following are methods used for inter process communication?#Messages#Shared ports#Interrupts#Whistles#Channels",
            "Which of the following are methods used for inter process communication?#Shared Memory#Shared ports#Interrupts#Whistles#Channels",
            "Which of the following are methods used for inter process communication?#Pipes#Shared ports#Interrupts#Whistles#Channels",
            "Which of the following are methods used for inter process communication?#Sockets#Shared ports#Interrupts#Whistles#Channels",
            "Which Item is not shared by all threads in a process?#Program Counter#Address Space#Global Variables#Open files",
            "Which Item is not shared by all threads in a process?#Stack#Address Space#Global Variables#Child processes",
            "User programs access operating systems services via#System calls#direct memory access#I/O interrupts#service calls",
            "User programs access operating systems services via#application programming interfaces (APIs), e.g. POSIX, Win32 API#direct memory access#I/O interrupts#service calls",
            "What sections does the image of a process in memory consist of?#Text#Pointer#Program Counter#Code",
            "What sections does the image of a process in memory consist of?#Data#Pointer#Program Counter#Code",
            "What sections does the image of a process in memory consist of?#Stack#Pointer#Program Counter#Code",
            "What sections does the image of a process in memory consist of?#Heap#Pointer#Program Counter#Code",
            "What is the POSIX system call Pthread_join used for?#To wait for a specific thread to finish#To start a new thread#To merge the results of two threads#To load a thread into the process address space in memory",
            "Inter process communication#allows processes to communicate and synchronize their actions without using the same address space#allows processes to communicate and synchronize their actions using the same address space#allows the processes to only synchronize their actions without communication#is the communication and synchronization between threads of the same process",
            "Inter process communication#is the communication and synchronization between two processes #allows processes to communicate and synchronize their actions using the same address space#allows the processes to only synchronize their actions without communication#is the communication and synchronization between threads of the same process",
            "A semaphore is a shared integer variable#That can not drop below zero#that can not drop below one#that can not be more than one#that can not be more than zero",
            "A semaphore is a shared integer variable#That can be more than two#that can not drop below one#that can not be more than one#that can not be more than zero",
            "If several processes access the same data at the same time and the result of the execution depends on the respective order in which the access takes place, this is called#a race condition#a dynamic condition#a safe condition#a critical condition",
            "How can a user interact with a linux System?#Bash#Bus#RFC#Kernel",
            "How can a user interact with a linux System?#Shell#Bus#RFC#Kernel",
            "How can a user interact with a linux System?#GUI#Bus#RFC#Kernel",
            "Debian, Ubuntu, Kali and Mint are#Operating Systems#Shell#Linux Kernels#Linus commands",
            "Debian, Ubuntu, Kali and Mint are#Linux Distributions#Shell#Linux Kernels#Linus commands",
            "Linux is#from Linus Torvalds#from Apple#from Microsoft#Proprietary",
            "Linux is#Open Source#from Apple#from Microsoft#Proprietary",
            "Linux is#Monolitic#from Apple#from Microsoft#Proprietary",
            "What should Linux/ Unix programs do? (Peter H. Salus)#Just one thing#As much as possible#None of them#Handle binary streams",
            "What should Linux/ Unix programs do? (Peter H. Salus)#Work together#As much as possible#None of them#Handle binary streams",
            "A computer system has 6 tape drives with n processes competing them. Each process may need 3 tape drivers What is the maximum value of n for which the system is guaranteed to be deadlock free?#2#4#6#1",
            "What statement about Bitmaps is true?#The larger the allocation unit, the smaller the bitmap#Bitmaps are always more efficient than linked lists#It is easy to find appropriate holves via bitmap#The smaller the allocation unit, the smaller the bitmap",
            "What statement about Bitmaps is true?#Bitmap is a list which shows if a unit is free or occupied #Bitmaps are always more efficient than linked lists#It is easy to find appropriate holves via bitmap#The smaller the allocation unit, the smaller the bitmap",
            "For which page replacement algorithm is the modified bit not needed?#First-In, First Out (FIFO)#Not Recently Used (NRU)#Clock#Second Chance#Not Frequently used (NFU)",
            "Scheduling is done by the operating system to#increase the throughput#decrease CPU utilization#increase the turnaround time#ikeep the CPU more idle",
            "Scheduling is done by the operating system to#increase CPU utilization#decrease CPU utilization#increase the turnaround time#ikeep the CPU more idle",
            "Which of the following conditions are required for a deadlock?#No resources can be forcibly removed from a process holding it#No two processes may be simultaneously inside their critical regions#No assumptions may be made about speeds or the number of CPUs#Preemptive scheduling",
            "Which of the following conditions are required for a deadlock?#A process may hold allocated resources while awaiting assignment of other resources#No two processes may be simultaneously inside their critical regions#No assumptions may be made about speeds or the number of CPUs#Preemptive scheduling",
            "Which of the following conditions are required for a deadlock?#Mutual exclusion#No two processes may be simultaneously inside their critical regions#No assumptions may be made about speeds or the number of CPUs#Preemptive scheduling",
            "Which of the following conditions are required for a deadlock?#Circular wait condition#No two processes may be simultaneously inside their critical regions#No assumptions may be made about speeds or the number of CPUs#Preemptive scheduling",
            "Binary Executables are stored in#/bin#/etc#/lib#/var",
            "Binary Executables are stored in#/usr/bin#/etc#/lib#/var",
            "Binary Executables are stored in#/sbin#/etc#/lib#/var",
            "What is a ready state of a process?#When the process is scheduled to run after some other processes execution#When the process has finished its execution#When the process is using the CPU#When all threads of a process are finished",
            "Your operating System is set up to detect and recover from dreadlocks - When should your OS not check for deadlocks?#When CPU utilization rises above threshold#Every time a resource request is made#When CPU utilization drops below threshold#Periodically (every n seconds)",
            "Typical Scheduling algorithms in interactive Systems are#Round-Robin-Scheduling#Shortest Job First#First-Come-First-Served#Shortest Remaining Time Next",
            "Typical Scheduling algorithms in interactive Systems are#Priority Scheduling#Shortest Job First#First-Come-First-Served#Shortest Remaining Time Next",
            "Which of the following is a process synchronization tool?#Semaphore#Socket#Pipe#Thread",
            "Which of the following is a process synchronization tool?#Mutex#Socket#Pipe#Thread",
            "A problem encountered in multiprogramming when a process is perpetually denied necessary resources is called#Starvation#Inversion#Deadlock#Exclusion",
            "Which of the following is true for the root directory of file systems?#There is no single root directory in Microsoft file systems#There is no single root directory in Unix-like file systems (Linux, MacOS,…)#'\\' is the root directory in Microsoft Windows file systems#'\\root' is the root directory in Unix-like operating systems (Linux, MacOS, …)",
            "Which of the following is true for the root directory of file systems?#'/' is the root directory in Unix-like operating systems (Linux, MacOS, …)#There is no single root directory in Unix-like file systems (Linux, MacOS,…)#'\\' is the root directory in Microsoft Windows file systems#'\\root' is the root directory in Unix-like operating systems (Linux, MacOS, …)",
            "The code that changes the value of a semaphore is#Critical section code#Non-Critical section code#Exclusion section code#Remainder section code",
            "Which memory type has the fastest access time?#Registers#Cache#RAM#SSD",
            "Which memory type has the fastest access time?#Cache#RAM#SSD#HDD",
            "If one thread opens a file with read privileges then#Other threads in the same process can also read from that file#Other threads in another process can also read from that file#Any other thread can not read from that file#Other threads in another process can also write to that file",
            "If one thread opens a file with read privileges then#No thread can write to that file#Other threads in another process can also read from that file#Any other thread can not read from that file#Other threads in another process can also write to that file",
            "Mutual exclusion can be provided by#binary semaphores#binary exclusions#critical regions#safe areas",
            "Mutual exclusion can be provided by#mutex locks#binary exclusions#critical regions#safe areas",
            "At a particular time of computation the value of a counting semaphore is 7. Then 20 acquire operations and 15 release operations were completed on these semaphore. The resulting value of the semaphore is#2#7#12#28",
            "What is returned by the POSIX' fork() system call?#The process ID (pid) of the created process#The process ID (pid) of the calling process#The user ID (uid) of the calling user#The user ID (uid) of the user associated with the new process",
            "Which of the following statements regarding memory is true?#Disk Memory (HDDs) is about 100 times cheaper than RAM, but data access is 1000 times slower compared to RAM#The size of the RAM of a 64-bit CPU is typically 64 x 64 bits#Moore's law states, that the number of CPUs on a chip doubles every 18 nmonths#SDD have smaller and faster moving parts than HDDs. Therefore data write/ read access is about 10 to 20 times faster",
            "Which of the following statements regarding memory is true?#Registers are CPU-internal memory, made of the same materials as the CPU#The size of the RAM of a 64-bit CPU is typically 64 x 64 bits#Moore's law states, that the number of CPUs on a chip doubles every 18 months#SDD have smaller and faster moving parts than HDDs. Therefore data write/ read access is about 10 to 20 times faster",
    };
    /*
    "QuestionCatalog" first creates a list of our question array. Then it shuffles it and chooses the first 16 questions. Then it saves those into the "shuffledQuestions" array.
    Only used for the exam mode at the moment.
    "getQuestion" splits one of our questions into 5 parts. # is used to divide those.
    "resetQuestionCatalog" chooses which question array to use depending on the topic chosen. The chosen array is saved in "shuffledQuestions" Sets our "arrayLength" for
    the practise mode. Resets "counter" to 0.
     */
    public QuestionCatalog() {
        List<String> stringList = Arrays.asList(math);
        Collections.shuffle(stringList);
        ArrayList<String> stringList2 = new ArrayList<>(stringList.subList(0,16));
        stringList2.toArray(shuffledQuestions);
    }

    public void resetQuestionCatalog(String chosenTopic){
        counter = 0;
        List<String> stringList = new ArrayList<>();
        switch(chosenTopic){
            case "MATH":
                stringList = Arrays.asList(math);
                arrayLength = math.length;
                break;
            case "BSYS":
                stringList = Arrays.asList(bsys);
                arrayLength = bsys.length;
                break;
            case "TEAM":
                stringList = Arrays.asList(team);
                arrayLength = team.length;
                break;
        }
        String[] practiseQuestions = new String[arrayLength];
        Collections.shuffle(stringList);
        if(sample.Main.getPractiseMode()){
            stringList.toArray(practiseQuestions);
            shuffledQuestions = practiseQuestions;
        }
        else {
            ArrayList<String> stringList2 = new ArrayList<>(stringList.subList(0, 16));
            stringList2.toArray(shuffledQuestions);
        }
    }
    public String[] getQuestion(){
        String s = shuffledQuestions[counter];
        counter++;

        return s.split("#");
    }
    public int getQuestionLength(){
        return arrayLength;
    }
}
