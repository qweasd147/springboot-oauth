import React from 'react';
import Typography from '@material-ui/core/Typography';
import Grid from '@material-ui/core/Grid';
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
  root: {
    ...theme.typography.button,
    backgroundColor: theme.palette.background.paper,
    padding: theme.spacing(1),
  },
  title: {
    marginLeft: theme.spacing(3),
    marginTop: theme.spacing(1)
  }
}));

export default function PaymentForm() {
    const classes = useStyles();

  return (
    <React.Fragment>
      <Grid container spacing={3}>
        <Grid item xs={12}>
            <div className={classes.root}>
                <Typography variant="h6" className={classes.title} >정보 제공 동의</Typography>
                <ul>
                    <li>email</li>
                    <li>id</li>
                    <li>...</li>
                </ul>
            </div>
        </Grid>
      </Grid>
    </React.Fragment>
  );
}