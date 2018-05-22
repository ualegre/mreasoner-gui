M-Reasoner GUI
======
A Java-based Integrated Development Environment for facilitating the development and implementation of rule-based context-aware systems in stationary platforms such as smart-homes, offices or hospitals. You can download the jar executable binaries [here](https://github.com/ualegre/mreasoner-gui/mreasoner-gui/bin/).  
The program has the structure shown in: (![[Figure 1]](https://github.com/ualegre/mreasoner-gui/documentation/structure.jpg))

* Graphical User Interface: The [mreasoner-gui]((https://github.com/ualegre/mreasoner-gui) is an additional layer to the mreasoner library which allows one to graphically configure, create and modify the different elements required for the reasoner to run. It includes the creation of rule specification files and its verification using the [m2nusmv](https://github.com/ualegre/m2nusmv) library. Also it includes a graphical tool for translating the output file of the LFPUBS using the lfpubs2m library.
* LFBPUS2M: The [lfpubs2m](https://github.com/ualegre/lfpubs2m) library automatically translates patterns detected using the pattern learning [LFPUBS](https://github.com/estibalitz/LFPUBS) tool into rule specification in M Language format. 
* MReasoner Core: The [mreasoner-core](https://github.com/ualegre/mreasoner-core) contains the minimal required elements for reasoning with the Forward Reasoning Algoritm. It is data-storage independent, and it can be used as a lightweight independent library.
* Vera Manager: In order to interconnect different types of Z-Wave radio based sensors, the M reasoning system uses [Vera routers](http://getvera.com/). These types of routers provide their own operating system to manage the inclusion and removal of different Z-Wave based sensors. The [vera-manager](https://github.com/ualegre/vera-manager) is a reasoner-independent library that facilitates reading the events occurring in the log of a Vera Router using an Observer pattern structure. 
* M2NuSMV: This library translates specifications written in the M language to specifications in the NuSMV model checker. In this way, allowing the formal verification of the rule specifications written in M.
* LFPUBS: Is an open-source system, available [here](https://github.com/estibalitz/LFPUBS),  that learns frequent patterns of user  behavior,  taking  into  consideration  the  specific  features  of  Intelligent Environents. It has a learning layer that is independent of the particular environment in which the system is being applied. It also includes a language  that  allows  the  representation  of  discovered  behaviors in  a  clear  and  unambiguous  way. Coupled with the learning language, it provides an algorithm that discovers frequent behaviors using association, workflow mining, clustering, and classification techniques.
* NuSMV: A symbolic model checker [tool](http://nusmv.fbk.eu/) that reimplements and extends SMV, the first model checker based on Binary Decision Diagrams using the CUDD library. NuSMV has been designed to be an open architecture for model checking, which can be reliably used for the verification of industrial designs,and as a test-bed for formal verification techniques.	

The reasoner provides an implementation of a simple reasoner 'C' with more precise and expressive references to conditions which have held for a while in the recent past. The reasoning language is compatible with the LFPUBS language that learns frequent patterns of user's behaviours. 

More information on the reasoning language can be found in: 
* [Temporal reasoning for intuitive specification of context-awareness](https://doi.org/10.1109/IE.2014.44)
* [NUSMV User Manual](http://nusmv.fbk.eu/NuSMV/userman/v25/nusmv.pdf)
* [Learning Frequent Behaviors of the Users in Intelligent Environments](https://doi.org/10.1109/TSMC.2013.2252892)

### Third party libraries
[vera_log_reader] (https://github.com/ualegre/vera_log_reader)  [GNU General Public License] (https://www.gnu.org/licenses/gpl-3.0.en.html)
[mreasoner] (https://github.com/ualegre/mreasoner) [GNU General Public License] (https://www.gnu.org/licenses/gpl-3.0.en.html)
[lfpubs2m] (https://github.com/ualegre/lfpubs2m) [GNU General Public License] (https://www.gnu.org/licenses/gpl-3.0.en.html)
[m2nusmv] (https://github.com/ualegre/m2nusmv) [GNU General Public License] (https://www.gnu.org/licenses/gpl-3.0.en.html)

## License 
## License 
This project is licensed under the [GNU General Public License v3.0](https://github.com/ualegre/mreasoner-gui/blob/master/LICENSE.md). 

## Developer Contact
This work has been created as part of the doctoral thesis contribution of Unai Alegre-Ibarra. The repository is open to pull-requests. 
* author: Unai Alegre-Ibarra
* e-mail: u.alegre@mdx.ac.uk
