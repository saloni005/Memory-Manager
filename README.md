# Simple Memory-Manager

Designed a memory manager for a new multitasking OS. The memory manager will allocate memory to processes and process will request memory for their variables.Considering below requirements: 
1. A process may request N contiguous blocks of memory Fail if unavailable 
2. A process can not be allocated more than a fraction of the total memory: 25% 
3. A process may request N blocks of memory at any point that it is alive 
4. A process may free previously allocated blocks of memory at any point in its lifetime.<br/>
<br/>
The available commands & their formats are described below: <br/>
1. allocate <br/>
allocate &lt;process_name> &lt;variable_name> &lt;blocks-requested> <br/>
2. free <br/>
free &lt;process_name, &lt;variable_name> <br/>
3. kill <br/>
kill &lt;process_name> <br/>
4. inspect <br/>
inspect &lt;processname> 


*******************************************************************************************************************************************************************************


**Input Format:** <br/>
&lt;total-block-count> <br/>
&lt;command-1> &lt;process> &lt;args> <br/>
  . <br/>
  . <br/>
&lt;command-N> &lt;process> &lt;args> <br/>
  <br/>
**Output Format:**<br/> 
&lt;command-result> &lt;allocated-block-count> / &lt;free-block-count> <br/><br/>
**Example:** <br/>
Input is in regular styled font. Output is in bold-italicized font. 

*100* <br/>
*allocate PI var_x 1000* <br/>
***error 0 / 100*** <br/>
*allocate P1 var_x 10* <br/>
***success 10 / 90*** <br/>
*allocate P2 var_y 25* <br/>
***success 35 / 65*** <br/>
*free P1 var_x* <br/>
***success 25 / 75*** <br/>
*kill P2* <br/>
***success 0 / 100*** <br/> 
*allocate P1 var_z 10* <br/>
***success 10 / 90*** <br/>
*allocate P4 var_x 5* <br/>
***success 15 / 85*** <br/>
*allocate P1 var_w 5* <br/>
***success 20 / 80*** <br/>
*ree P4 var_x* <br/>
***success 15 / 85*** <br/> 
*allocate PI var_y 6* <br/>
***success 21 / 79*** <br/>
*inspect P1* <br/>
***var_z 0-9*** <br/>
***var_w 15-19*** <br/>
***var_y 20-25*** <br/>

*******************************************************************************************************************************************************************************
