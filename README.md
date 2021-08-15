# code_formatter

<pre>
if (boo == null){ return 1;}
else if (boo == 1)   {      return 2; }    
   else 
{ if (boo > 10) 
   {
boo -= 1;    
return boo;
 }  
      return 4;
} 
</pre>

---> 
<pre>
if (boo == null) {
    return 1;
}
else if (boo == 1) {
    return 2;
}
else {
    if (boo > 10) {
        boo -= 1;
        return boo;
    }
    return 4;
}
</pre>
