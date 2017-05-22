#!/bin/sh

# inputs
ip="192.168.1.121"
userid="pi"
password="raspberry"
path_to_binary="/home/uwlau/DAEbot/Devices/rPI_Operator/Source/rPI_Operator/Debug"
binary_name="rPI_Operator"

/usr/bin/expect << EOF

puts "\n** RHTools -> MAKE BINARY EXECUTEABLE" 

spawn ssh ${userid}@$ip chmod +x $binary_name
expect {
  -re ".*es.*o.*" {exp_send "yes\r"; exp_continue }
  -re ".*sword.*" {exp_send "$password\r"; exp_continue }
  eof
   }
puts "\n** RHTools -> done."
EOF

