count =0
found=0;

def buildVowelDictionaryNFind(depth,prevStr,word):
    global count, found
    vowels = [' ', 'A','E','I','O','U']
    
    startIdx = endIdx = 1 
    if depth!=1 :                           startIdx = 0
    if depth==1 or prevStr[-1]!=' ' :        endIdx = len(vowels)
        
    for i in range(startIdx,endIdx):
        v = vowels[i]
        
        if(depth<5):
            buildVowelDictionaryNFind(depth+1,prevStr+v,word)
        else:
            if(word!=prevStr+v):             count=count+1
            else:                            found=count+1
                        
        if(found): 
            break;
            
def solution(word):
    
    vowelDictionary = [];
    while(len(word)<5):
        word += ' ';
    
    buildVowelDictionaryNFind(1,"", word);
    
    return found