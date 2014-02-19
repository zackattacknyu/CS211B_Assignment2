%{

The following is heavily based on slide 22-23 of the following lecture:
    http://graphics.ucsd.edu/courses/cse168_s06/ucsd/lecture03.pdf

For this script, we are finding the refraction vector given the following:
    N: normal vector
    E: eye vector from surface point
    n_ref: refraction index for refraction vector material
    n_eye: refraction index for eye vector material

For notation, we will use the following: 
    R: refraction vector
    cos_phi = cosine of the refraction vector with the negative of
                the normal vector. Used during calculations
    R_vert = vertical component of the refraction vector. Used during
                the calculation

The formula will be as follows
    R_vert = (n_eye)(N*(N.E) - E)/(n_ref)
    cos_phi = sqrt( 1 - ( n_eye/n_ref )^2 (1 - (N.E)^2 ) )
    R = R_vert - N*cos_phi
%}

n_eye = 1.0;
n_ref = 1.3;
E = [-1 1 0];
N = [0 1 0];

E = E/norm(E); %normalize the eye vector
N = N/norm(N); %makes sure the normal vector is normalized