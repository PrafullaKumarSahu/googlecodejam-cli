# Google Code Jam - CLI

[![Build Status](https://travis-ci.org/Faylixe/googlecodejam-cli.svg?branch=master)](https://travis-ci.org/Faylixe/googlecodejam-cli) [![Join the chat at https://gitter.im/Faylixe/googlecodejam-client](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/Faylixe/googlecodejam-client?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

Command line application for managing Google Code Jam contest submission based on the [googlecodejam-client](https://github.com/Faylixe/googlecodejam-client) API. It requires Java 8.

* [Disclaimer](#disclaimer)
* [Usage](#usage)
  - [Installation](#installation)
  - [Initialization action](#initialization-action)
  - [Download action](#download-action)
  - [Submit action](#submit-action)
* [Jammy](#jammy)


## Disclaimer

Please note that this client is not provided by Google. Any responsability is declined if a bug occurs when you are using it in a real contest condition.

## Usage

This command line application consists in the client JAR and a running script is also available. Here is the usage description :

```bash
codejam action parameter
```

Where action belongs to the following option list :

* *--init*
* *--download*
* *--submit*

Please note that Firefox web browser is also required.

### Installation

You can install script and packaged version of client by running the following command :

```bash
wget -O - https://raw.githubusercontent.com/Faylixe/googlecodejam-cli/master/scripts/install | bash
```

Once script has been executed, you can run the **codejam** command. Please note that installation script should be ran under root permission.

If you are not comfortable with running script from an unknown source with root permission, you can just download the last released
[jar file](https://github.com/Faylixe/googlecodejam-cli/releases), and run it using the command :

```java
java -jar googlecodejam-cli.jar action parameters
```

### Initialization action

This action is in charge of retrieving a **SACSID** cookie value which is mandatory for querying
Code Jam platform successfully. This cookie can be either retrieved using :

* Firefox browser
* Raw cookie input

By default the Firefox method is used, which will open up a Firefox instance
in order to authenticate to Google services. Once Firefox is opened and the login page loaded,
please proceed to the authentication process, and when you will be logged and redirected
to the code jam home page, Firefox will be closed automatically.

```bash
codejam --init
```

If you want to directly supply the **SACSID** cookie value you can use the *--method* parameter : 

```bash
codejam --init --method text
```

Once logged you will be prompted to choose a contest and a round. Those will become contextual round and session
for the current directory you are running the script in, meaning that if you run another time the script with another
action, it will use the created contextual logged session and round.

Although you can also specify a round directly using the *--contest* parameter. It takes in argument the round dashboard id
that you can find in your dashboard URL like following :

![dashboard url](https://raw.githubusercontent.com/Faylixe/googlecodejam-cli/master/dashboardurl.png)

For this URL, the corresponding command would be :

```bash
codejam --init --contest 6224486
```

This would be particulary helpful during real contest where the round is not indexed.

### Download action

As it name suggests, the *download* action allows logged user to download an input file for a given problem.
If the contest is active, then it will trigger the submission timer depending of the input difficulty you have
choosen (usually 4 minutes for a *small* input, 8 for a *large* one).

The following exemple will download the *small* input file for the first problem.

```bash
codejam --download --problem A --inputtype small
```

If the download is successful, the name of the downloaded file will be printed, so it could be chained in a command workflow, for example :

```bash
cat < `codejam --download --problem A --inputtype small` | python A.py
```

Please note that for problem with two small dataset, the dataset name would be **small1** and **small2**

Plus, if you have to download several time the same input file (because you have failed a submission for example), you can specify an optional parameter *--attempt* which takes an integer as argument. It will save the file with a different suffix. The following command :

```bash
codejam --download --problem A --inputtype small --attempt 1
```

will save the input file using **A-small-1.in** as filename.

### Submit action

Once input file is downloaded, and algorithm solved all test cases, *submit* action could be used in order
to send either the output file as the source file of your algorithm as well.

```bash
codejam --submit --problem A --inputtype small --output path/to/output --sourcefile path/to/sourcefile
```

## Issues

Please do not hesitate to report any bug [here](https://github.com/Faylixe/googlecodejam-cli/issues). For each issue please deliver the output of the command you ran using the *--verbose* option.

## Jammy

Want more than a command line application ? Take a look at the [Jammy](http://faylixe.fr/jammy) project, which provides a dedicated development perspective
in Eclipse IDE.
