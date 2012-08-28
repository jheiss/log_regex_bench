# Overview #

In running large Unix environments I've found it useful to watch for and
alert on syslog messages indicating problems.  The easiest way to do
this is to have a list of regular expressions for messages you know to
be problems.  Addtionally it can be useful to maintain a list of regular
expressions for messages that you known to not be problems.  Anything
not matching either list represents a message you haven't seen before,
which can be set aside in a file for occasional review for new types of
problem messages.

These lists (particularly the list of known good messages) can be quite
long.  The known good list I've built up has over 1000 regular
expressions.  Whether you do the matching on each client or on a central
server passing all your syslog messages through many regular expressions
takes a lot of CPU.

This project aims to benchmark the regular expression functionality of
common programming languages to see if there are languages that would be
best for this application.

