#!/bin/bash
status=1
while [ $status -ne 0 ]  
do
    mysqladmin ping --port 3333 -u root --password=Boomersooner7 2> /dev/null
    status=$?
    echo "Database not started yet... waiting..."
    sleep 5
done