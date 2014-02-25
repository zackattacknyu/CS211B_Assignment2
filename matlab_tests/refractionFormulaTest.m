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
    ratio = n_eye/n_ref
    cos_theta = N.E
    R_vert = ratio*(E - N*(cos_theta))
    cos_phi_squared = 1 - ( ratio )^2 (1 - (cos_theta)^2 ) 
    R = R_vert - N*sqrt(cos_phi_squared)
%}

n_eye = 1.0;
n_ref = 1.0;
E = [-1 1 0];
N = [0 1 0];

R = refractionDirection(n_eye,n_ref,E,N)

