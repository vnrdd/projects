#ifndef FFT_H
#define FFT_H
#include <cmath>
#include <complex>
#include <vector>
typedef std::complex<long double> cld;

bool writeSign(const bool &a, const bool &b);
size_t setSize(size_t v);
int reverseBits(int i, int n);
std::vector<cld> generalFFT(std::vector<cld> &v, cld q);
std::vector<cld> FFT(std::vector<cld> &v);
std::vector<cld> inverseFFT(std::vector<cld> &v);

#endif  //FFT_H