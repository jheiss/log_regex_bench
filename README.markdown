# Overview #

In running large Unix environments I've found it useful to watch for and
alert on syslog messages indicating problems.  The easiest way to do
this is to have a list of regular expressions for messages you know to
be problems.  Additionally it can be useful to maintain a list of regular
expressions for messages that you know are not problems.  Anything
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

# Improvements Welcome #

My intention is to fairly compare various languages for the log
monitoring use case, so if you see places where the code could be
improved for performance let me know through an issue or pull request.

