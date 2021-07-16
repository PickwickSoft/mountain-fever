import os
import pathlib
import csv
from quotecrawler.parse import quotes, category

# Demo: read all quotes from every category in the list returned by category()
# category() returns all relevant categories


# Get the projects root directory
base_dir = os.path.dirname(os.path.realpath(
    __file__).replace("/tools/quotes", ""))

data_file = base_dir + "/data/quotes/quotes.csv"


# Create <project root>/data/quotes directory structure if not exists
if not os.path.isdir(base_dir + "/data/quotes/"):
    if not os.path.isdir(base_dir + "/data/"):
        os.mkdir(base_dir + "/data")
    os.mkdir(base_dir + "/data/quotes/")


# field names
fields = ['Quote', 'Author']

with open(data_file, "w+") as file:
    # creating a csv writer object
    csvwriter = csv.writer(file)
    csvwriter.writerow(fields)
    for i in category():
        print("Category '%s'" % i)

        # quotes() gets the quotes given a category name and a number of desired quotes
        demoResult = quotes(i, -1)
        print("Got %d quotes" % len(demoResult))

        for quote in demoResult:
            csvwriter.writerow([quote.quote.replace(
                "\n", ""), quote.author.replace("\n", "")])
