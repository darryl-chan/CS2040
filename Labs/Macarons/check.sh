#!/bin/bash
exec 2> /dev/null

function ctrl_c() {
    kill -9 $pid
    kill -9 $waiter
    exit 2
}
trap ctrl_c INT

if [ -z "$1" ]
then
    echo "Problem name is required as the first argument."
    echo "eg: ./check.sh HelloWorld"
    exit 0
    
fi
problem="$1"    #problem name as first variable

#compiling .java file
if ! javac *.java;
then
    echo "Compilation Failed"
    exit 0;
else
    echo "Compiled $problem.java successfully"
fi

#enumerate all .in files in the same directory, in natural order
array=($(ls -1 *.in | sed -e 's/^\([a-zA-Z]*\)\([0-9]*\)\(.*\)/\1\3 \2 \1\2\3/' | sort -k 1,1 -k 2,2n | sed -e 's/^.* //'))
for i in "${array[@]}"
do
    # Spawn a child process. Time limit: 5s
    java $problem < $i > ${i/.in/.o} 2>/dev/null & pid=$!
    (sleep 5 && kill -9 $pid) & waiter=$!
    # wait on our worker process and return the exitcode
    wait $pid 
    exitcode=$?
    # kill the waiter subshell, if it still runs
    kill -9 $waiter
    
    if [ $exitcode -eq 137 ]
    then
        echo ${i/.in}": Time Limit Exceeded"
    elif [ $exitcode -ne 0 ]
    then
        echo ${i/.in}": Program crashed / produced non-zero exit code"
    else
        results=$( diff ${i/.in/.o} ${i/.in/.out} > ${i/.in/.diff} 2> ${i/.in/.differr} )
        if [ $? -eq 0 -a ! -s ${i/.in/.differr} ]     #check if there is a difference between the two files
        then
            echo ${i/.in}": Correct"
            rm ${i/.in/.o} ${i/.in/.diff}
        elif [ -s ${i/.in/.differr} ]
        then
            echo ${i/.in}": Missing newline at EOF"
            rm ${i/.in/.o} ${i/.in/.diff}
        else
            echo ${i/.in}": Incorrect"
            echo "Output in ${i/.in/.o}, diff in ${i/.in/.diff} (< your output | > official output)"
        fi
        rm ${i/.in/.differr}
    fi
done


