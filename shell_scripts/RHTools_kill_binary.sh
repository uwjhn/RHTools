#!/usr/bin/env bash

#Source config file from argument
echo  "** RHTools -> Using Config: $1"
source $1

/usr/bin/expect << EOF

puts "\n** RHTools -> KILL BINARY ACTIVITIES" 

spawn ssh ${userid}@$ip pkill $binary_name
expect {
  -re ".*es.*o.*" {exp_send "yes\r"; exp_continue }
  -re ".*sword.*" {exp_send "$password\r"; exp_continue }
  eof
   }
puts "\n** RHTools -> done."
EOF

