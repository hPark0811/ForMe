import sys

from td_davinci_api_processor import get_customer_array
import pickle
import numpy as np

import matplotlib.pyplot as plt


def return_value(id, km):
    return km.predict([get_customer_array(id)])[0]


PARAMS = [
    'age',
    'income',
    'food and dining',
    'shopping',
    'home',
    'entertainment',
    'fees and charges',
    'food and dining count',
    'shopping count',
    'home count',
    'entertainment count',
    'fees and charges count'
]

if __name__ == '__main__':

    with open('model.pkl', 'rb') as file:
        model = pickle.load(file)
        c = model.predict([get_customer_array(sys.argv[1])])[0]
        print(c)
