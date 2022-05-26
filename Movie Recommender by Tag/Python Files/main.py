#Import packages
import collections
import gensim
import nltk
from gensim.models.doc2vec import Doc2Vec, TaggedDocument
from nltk.tokenize import word_tokenize
import random
lines = open(r'C:\Users\Joshg\OneDrive\Desktop\maw\movies.txt', encoding='utf-8').read()
myline = lines.splitlines()

print("Selecting Movies...")
x = random.randint(1, 10000)
print(myline[x].split(',')[1])

y = random.randint(1, 10000)
print(myline[y].split(',')[1])

z = random.randint(1, 10000)
print(myline[z].split(',')[1])

print("Building model...")

# Tokenization of each document
tokenized_doc = []
tokenizer = nltk.RegexpTokenizer(r'\w+')
f = open(r"C:\Users\Joshg\OneDrive\Desktop\maw\myfile", "r")

#spliting input
for d in f:
    d.split('[')
    tokenized_doc.append(tokenizer.tokenize(d.split('[')[1]))

tagged_data = [TaggedDocument(d, [i]) for i, d in enumerate(tokenized_doc)]
model = gensim.models.doc2vec.Doc2Vec(vector_size=50, min_count=1, epochs=40)

model.build_vocab(tagged_data)

print("Training model...")
model.train(tagged_data, total_examples=model.corpus_count, epochs=model.epochs)

print("Computing Average Vector from given movies...")
vectorx = model.infer_vector(tokenized_doc[x])
vectory = model.infer_vector(tokenized_doc[y])
vectorz = model.infer_vector(tokenized_doc[z])

avg = (vectorx + vectory + vectorz) / 3

print("Determining Recommendation...")

ranks = []
second_ranks = []
for doc_id in range(len(tagged_data)):
    inferred_vector = avg
    sims = model.dv.most_similar([inferred_vector], topn=len(model.dv))
    rank = [docid for docid, sim in sims].index(doc_id)
    ranks.append(rank)

    second_ranks.append(sims[1])

for label, index in [('MOST', 0), ('SECOND-MOST', 1), ('THIRD-MOST', 3), ('LEAST', len(sims) - 1)]:
    print(u'%s %s: «%s»' % (label, sims[index], ' '.join(tagged_data[sims[index][0]].words)))
    temp = sims[index]
    print(myline[int(temp[0])].split(',')[1])
    print()





