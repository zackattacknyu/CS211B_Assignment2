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

n_eye = 2.75;
n_ref = 1.0;

E1 = [0.279529 -0.217648 0.935143]; %(2.75 2.75) case
N1 = [1 0 0];

E2 = [-0.937070 -0.079145 0.340052]; %(2.75 1.2) case
N2 = [-1 0 0];

N=N/norm(N);
E1 = E1/norm(E1);
E2 = E2/norm(E2);
E1 = -E1;
E2 = -E2;
N1 = -N1;
N2 = -N2;
R1 = refractionDirection(n_eye,n_ref,E1,N1)
R2 = refractionDirection(n_eye,n_ref,E2,N2)
