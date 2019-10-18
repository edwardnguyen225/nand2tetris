#include "linkedList.h"

struct TRam
{
public:
    int address;
    int value;
};

class TDataset
{
    public:
        LinkedList<TRam> *ramList = new LinkedList<TRam>();
};