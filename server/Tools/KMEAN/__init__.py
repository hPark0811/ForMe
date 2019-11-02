import sys

from model import model
from td_davinci_api_processor import get_customer_array

if __name__=='__main__':
    # kmean_model = model()
    #print(sys.argv[1])
    km, train_set, test_set = model()
    #    print(km.predict([get_customer_array(sys.argv[1])])[0])

    c = km.predict([get_customer_array(sys.argv[1])])[0]
    
    print(str("{creditCard: 'cashback_infinite', bankAccount: 'asdfasdfasfd'}"))
    """
    argv 0 = __init__.py
    argv 1 = customer id
    """