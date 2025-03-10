import React from 'react';
import { Container, Grid, TextField, Button, Typography } from '@mui/material';
import 'bootstrap/dist/css/bootstrap.min.css';
 
export default function Signup() {
  return (
    <div>
      {/* Bootstrap Navbar */}
      <nav className="navbar navbar-expand-lg navbar-dark bg-primary shadow-sm">
        <div className="container">
          <a className="navbar-brand fw-bold" href="#">To-Do List</a>
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarNav"
            aria-controls="navbarNav"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarNav">
            <ul className="navbar-nav ms-auto">
              <li className="nav-item">
                <a className="nav-link fw-semibold" href="#">Login</a>
              </li>
              <li className="nav-item">
                <a className="nav-link fw-semibold" href="#">Sign Up</a>
              </li>
              <li className="nav-item">
                <a className="nav-link fw-semibold" href="#">Add Task</a>
              </li>
            </ul>
          </div>
        </div>
      </nav>
 
      {/* Form Section */}
      <Container maxWidth="md" sx={{ mt: 5, p: 4, bgcolor: 'white', borderRadius: 2, boxShadow: 3 }}>
        <Typography variant="h4" align="center" gutterBottom>
          Sign-Up
        </Typography>
        <Grid container spacing={2}>
          <Grid item xs={6}>
            <TextField fullWidth label="First Name" variant="outlined" />
          </Grid>
          <Grid item xs={6}>
            <TextField fullWidth label="Last Name" variant="outlined" />
          </Grid>
          <Grid item xs={12}>
            <TextField fullWidth label="Username" variant="outlined" />
          </Grid>
          <Grid item xs={12}>
            <TextField fullWidth label="Email" type="email" variant="outlined" />
          </Grid>
          <Grid item xs={12}>
            <TextField fullWidth label="Date of Birth" type="date" InputLabelProps={{ shrink: true }} variant="outlined" />
          </Grid>
          <Grid item xs={12}>
            <TextField fullWidth label="Address" variant="outlined" />
          </Grid>
          <Grid item xs={12}>
            <TextField fullWidth label="Phone Number" type="tel" variant="outlined" />
          </Grid>
          <Grid item xs={12}>
            <TextField fullWidth label="Password" type="password" variant="outlined" />
          </Grid>
          <Grid item xs={12}>
            <Button fullWidth variant="contained" color="primary">
              Signup
            </Button>
          </Grid>
        </Grid>
      </Container>
 
      {/* Bootstrap Footer */}
      <footer className="bg-dark text-light mt-5 py-4">
        <div className="container text-center">
         
          <p className="mb-0">© 2024 Company, Inc. All rights reserved.</p>
        </div>
      </footer>
    </div>
  );
}
 
