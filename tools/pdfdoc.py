# Script for generating documentation in
# PDF format instead of JavaDoc's default
# HTML.
#
# See: http://pdfdoclet.sourceforge.net/running.html

import sys
from os import system as exec

REQUIRED = [
    "-classpath",
    "-sourcepath",
    "-docletpath",
    "-doclet",
    "-pdf",
    "-packages"
]

def read_options(file):
    with open(file) as f:
        lines = [l.strip() for l in f.readlines()]
        return [l for l in lines if not l.startswith("#")] # allow comments :^)


def check_required(options):
    provided = [option.split(" ")[0] for option in options]
    missing = [r for r in REQUIRED if not r in provided]
    return missing


def get_packages(options):
    for o in options:
        if o.startswith("-packages"):
            return " ".join(o.split(" ")[1:])


def main(options_file):
    options = read_options(options_file)
    missing = check_required(options)

    if len(missing) > 0:
        print("Error:\n\tMissing required options: {}".format(missing))
        return
    
    packages = get_packages(options)
    options = [o for o in options if not o.startswith("-packages")]

    cmd = "javadoc {} {}".format(" ".join(options), packages)
    exec(cmd)
    print("----- pdfdoc.py -----")


if __name__ == "__main__":
    if len(sys.argv) < 2:
        print("USAGE:")
        print("\tpython pdfdoc.py <path to .options file")
        sys.exit(1)

    main(sys.argv[1])
