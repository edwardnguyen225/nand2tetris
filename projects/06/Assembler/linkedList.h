#include <string>
#include <stdio.h>
#include <string.h>
#include <iostream>

using namespace std;

template <class T>
struct LinkedItem
{
    T data;
    LinkedItem<T> *pNext;
    LinkedItem() : pNext(NULL) {}
    LinkedItem(T &a) : data(a), pNext(NULL) {}
};

template <class T>
class LinkedList
{
    LinkedItem<T> *_pHead; // The head pointer of linked list
    size_t _size;          // number of elements in this list
    int idMax;             // maximum id in dataset
public:
    LinkedList() : _pHead(NULL), _size(0), idMax(-1) {}
    ~LinkedList();

    void clean();
    bool isEmpty()
    {
        return _pHead == NULL;
    }
    size_t getSize()
    {
        return _size;
    }
    int getIdMax()
    {
        return idMax;
    }
    void setIdMax(int _id)
    {
        if (_id > idMax)
            idMax = _id;
    }

    T &at(int i);         // give the reference to the element i-th in the list
    T &operator[](int i); // give the reference to the element i-th in the list

    bool find(T &a, int &idx); // find an element similar to a in the list. Set the found index to idx, set idx to -1 if failed. Return true if success.
    int insert(int i, T &a);   // insert an element into the list at location i. Return 0 if success, -1 otherwise
    int remove(int i);         // remove an element at position i in the list. Return 0 if success, -1 otherwise.

    int push_back(T &a);  // insert to the end of the list
    int insertHead(T &a); // insert to the beginning of the list

    int removeHead(); // remove the beginning element of the list
    int removeLast(); // remove the last element of the list

    LinkedItem<T> *getItem(int i); // get an element at position i in the list. Return 0 if success, -1 otherwise

    void reverse();

    void traverse(void (*op)(T &))
    {
        // TODO: Your code goes here
    }
    void traverse(void (*op)(T &, void *), void *pParam)
    {
        // TODO: Your code goes here
    }
};

template <class T>
LinkedList<T>::~LinkedList()
{
    this->clean();
}

///  Clear linked list
template <class T>
void LinkedList<T>::clean()
{
    LinkedItem<T> *pTmp;
    while (this->_pHead != NULL)
    {
        pTmp = this->_pHead;
        this->_pHead = this->_pHead->pNext;
        delete pTmp;
    }
    this->_size = 0;
}

/// Insert an element into the list at location i
/// Return 0 if success, -1 otherwise
template <class T>
int LinkedList<T>::insert(int i, T &a)
{
    if (i < 0 || i > this->_size)
        return -1;

    LinkedItem<T> *pNew, *pPre;
    pNew = new LinkedItem<T>(a);
    if (pNew == NULL)
        return -1;

    if (_pHead == NULL)
    {
        _pHead = pNew;
        pNew->pNext = NULL;
    }
    else if (i == 0)
    {
        pNew->pNext = _pHead;
        _pHead = pNew;
    }
    else
    {
        pPre = this->_pHead;

        for (int j = 0; j < i - 1; j++)
            pPre = pPre->pNext;

        pNew->pNext = pPre->pNext;
        pPre->pNext = pNew;
    }

    this->_size++;
    return 0;
}

/// Remove an element at position i in the list
/// Return 0 if success, -1 otherwise
template <class T>
int LinkedList<T>::remove(int i)
{
    if (i < 0 || i > this->_size)
        return -1;

    LinkedItem<T> *pDel, *pPre;
    if (i == 0)
    {
        pDel = _pHead;
        _pHead = _pHead->pNext;
    }
    else
    {
        pPre = this->_pHead;

        for (int j = 0; j < i - 1; j++)
            pPre = pPre->pNext;

        pDel = pPre->pNext;
        pPre->pNext = pDel->pNext;
    }

    delete pDel;
    this->_size--;
    return 0;
}

/// Insert item to the end of the list
/// Return 0 if success, -1 otherwise
template <class T>
int LinkedList<T>::push_back(T &a)
{
    // TODO: Your code goes here
    return this->insert(this->_size, a);
}

/// Insert item to the front of the list
/// Return 0 if success, -1 otherwise
template <class T>
int LinkedList<T>::insertHead(T &a)
{
    // TODO: Your code goes here
    return this->insert(0, a);
}

/// Remove the first item of the list
/// Return 0 if success, -1 otherwise
template <class T>
int LinkedList<T>::removeHead()
{
    // TODO: Your code goes here
    return this->remove(0);
}

/// Remove the last item of the list
/// Return 0 if success, -1 otherwise
template <class T>
int LinkedList<T>::removeLast()
{
    // TODO: Your code goes here
    return this->remove(this->_size);
}

/// Get an element at position i in the list
/// Return 0 if success, -1 otherwise
template <class T>
LinkedItem<T> *LinkedList<T>::getItem(int i)
{
    LinkedItem<T> *dataOut;
    if (i < 0 || i > this->_size)
        return 0;

    LinkedItem<T> *pCurr;

    if (_pHead == NULL)
    {
        return 0;
    }
    else if (i == 0)
    {
        pCurr = _pHead;
        dataOut = pCurr;
    }
    else
    {
        pCurr = this->_pHead;

        for (int j = 0; j < i - 1; j++)
            pCurr = pCurr->pNext;

        dataOut = pCurr;
    }
    return dataOut;
}
