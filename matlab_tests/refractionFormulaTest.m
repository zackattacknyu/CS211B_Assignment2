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
E1 = [-1 1 0];
E2 = [2 1 0];
E3 = [-3 1 0];
E4 = [5 1 0];
E5 = [-6 1 0];
E6 = [3 2 0];
N = [1 8 0];
O = [0 0 0];

N=N/norm(N);
E1 = E1/norm(E1);
E2 = E2/norm(E2);
E3 = E1/norm(E3);
E4 = E1/norm(E4);
E5 = E1/norm(E5);
E6 = E1/norm(E6);

R1 = refractionDirection(n_eye,n_ref,E1,N);
R2 = refractionDirection(n_eye,n_ref,E2,N);
R3 = refractionDirection(n_eye,n_ref,E3,N);
R4 = refractionDirection(n_eye,n_ref,E4,N);
R5 = refractionDirection(n_eye,n_ref,E5,N);
R6 = refractionDirection(n_eye,n_ref,E6,N);

normalx = [N(1) 0 -N(1)];
normaly = [N(2) 0 -N(2)];

set1x = [E1(1) 0 R1(1)];
set1y = [E1(2) 0 R1(2)];

set2x = [E2(1) 0 R2(1)];
set2y = [E2(2) 0 R2(2)];

set3x = [E3(1) 0 R3(1)];
set3y = [E3(2) 0 R3(2)];

set4x = [E4(1) 0 R4(1)];
set4y = [E4(2) 0 R4(2)];

set5x = [E5(1) 0 R5(1)];
set5y = [E5(2) 0 R5(2)];

set6x = [E6(1) 0 R6(1)];
set6y = [E6(2) 0 R6(2)];

plot(set1x,set1y,set2x,set2y,set3x,set3y,set4x,set4y,set5x,set5y,set6x,set6y,normalx,normaly)