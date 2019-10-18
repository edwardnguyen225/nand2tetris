#include "dbLib.h"
#include <iostream>
#include <fstream>
#include <iomanip>
#include <string>
#include <cstring>
#include <sstream>
#include <math.h>

using namespace std;

int main()
{
    string fileName = "";
    ifstream fileIn(fileName);

    TDataset *pData = new TDataset;
    try
    {
        if (!fileIn.is_open())
            cout << "ERROR: could not open file" << endl;

        string tmpStr;
        while (!fileIn.is_open())
        {
            getline(fileIn, tmpStr);
            
        }
        
    }
    catch (const exception &e)
    {
        std::cerr << e.what() << '\n';
    }
}