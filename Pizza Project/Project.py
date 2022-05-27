import inline as inline
import matplotlib
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

#%matplotlib inline

import warnings
warnings.filterwarnings('ignore')

pizza2=pd.read_csv('C:\\...input\pizza_v2.csv')
df = pizza2

df.shape
df['price']=df['price_rupiah'].str.replace('Rp','').str.replace(',','').astype(int)
df.drop(['price_rupiah'],axis=1,inplace=True)

from sklearn.preprocessing import LabelEncoder
le=LabelEncoder()

features=(['company', 'topping', 'variant', 'size', 'extra_sauce',
       'extra_cheese', 'extra_mushrooms'])

for col in features:
    df[col]=le.fit_transform(df[col])

from sklearn.model_selection import train_test_split

X=df[features]
y=df.price

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2,random_state=12)

from sklearn.metrics import r2_score

from sklearn.tree import DecisionTreeRegressor

model_dt= DecisionTreeRegressor(random_state=12)
model_dt.fit(X_train,y_train)
pred_dt=model_dt.predict(X_test)


print('Testing R2 Score: ', r2_score(y_test, pred_dt)*100)
from sklearn.metrics import classification_report, confusion_matrix, accuracy_score
print('Test accuracy: ',accuracy_score(y_test, pred_dt))


from sklearn.ensemble import  RandomForestRegressor
from sklearn.metrics import classification_report, confusion_matrix, accuracy_score

model_rf= RandomForestRegressor(n_estimators=100, random_state=0)
model_rf.fit(X_train,y_train)
pred_rf= model_rf.predict(X_test)

print('Testing R2 Score: ', r2_score(y_test, pred_rf)*100)
print('Test accuracy: ',model_rf.score(X_test, y_test))
