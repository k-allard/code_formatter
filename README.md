# code_formatter ( :construction: *in progress* :construction: )

### Build:
```mvn package```

### Run:
```java -jar code_formatter-0.0.1-jar-with-dependencies.jar code_input.txt```

### Example:

<pre>
if (boo == null){ return 1;}
else if 
 (boo == 1)   {      return 2; }    
   else 
{ for (boo = 0; boo > 10; boo += 8)
   {
boo -= 1;         }
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
    for (boo = 0; boo > 10; boo += 8) {
        boo -= 1;
    }
    return 4;
}

</pre>
