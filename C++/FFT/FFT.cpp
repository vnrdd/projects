#include "FFT.h"

bool writeSign(const bool &a, const bool &b) {
    if (a == 0 && b == 0)
        return 1;
    else
        return a * b;
}

size_t setSize(size_t v) {
    int n = 1;
    while (n < v) n *= 2;
    n *= 2;
    return n;
}

int reverseBits(int i, int n) {
    int ans = 0;
    for (; n > 1; n /= 2) {
        ans = ans * 2 + (i % 2);
        i /= 2;
    }
    return ans;
}

std::vector<cld> generalFFT(std::vector<cld> &v, cld q) {
    if (v.size() == 1) return v;

    for (int i = 0; i < v.size(); ++i) {
        int revi = reverseBits(i, v.size());
        if (i < revi)
            std::swap(v[i], v[revi]);
    }

    for (int l = 2; l <= v.size(); l *= 2) {
        cld ql = q;
        for (int ll = l; ll < v.size(); ll *= 2)
            ql *= ql;
        for (int j = 0; j != v.size(); j += l) {
            cld w(1.0, 0.0);
            for (int i = 0; i < l / 2; ++i) {
                cld a = v[j + i];
                cld b = w * v[j + l / 2 + i];
                v[j + i] = a + b;
                v[j + l / 2 + i] = a - b;
                w *= ql;
            }
        }
    }
    return v;
}

std::vector<cld> FFT(std::vector<cld> &v) {
    long double ang = 2.0 * M_PI / v.size();
    return generalFFT(v, cld(std::cos(ang), std::sin(ang)));
}

std::vector<cld> inverseFFT(std::vector<cld> &v) {
    long double ang = 2.0 * M_PI / v.size();
    v = generalFFT(v, cld(std::cos(ang), -std::sin(ang)));
    for (int i = 0; i < v.size(); ++i) v[i] /= v.size();
    return v;
}