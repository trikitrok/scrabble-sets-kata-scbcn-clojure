# scrabble-sets

[Scrabble Sets kata](https://gist.github.com/trikitrok/c2935f6f40c2f1f12d0118af38ca1c68)

## How to run the tests

The project uses [Midje](https://github.com/marick/Midje/).

`lein midje` will run all tests.

`lein midje namespace.*` will run only tests beginning with "namespace.".

`lein midje :autotest` will run all the tests indefinitely. It sets up a
watcher on the code files. If they change, only the relevant tests will be
run again.
