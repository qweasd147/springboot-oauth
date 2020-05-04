import React, { useRef, useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Button from '@material-ui/core/Button';
import Link from '@material-ui/core/Link';
import Typography from '@material-ui/core/Typography';
import Informations from '../component/informations';

const Copyright = ()=>{
  return (
    <Typography variant="body2" color="textSecondary" align="center">
      {'Copyright © '}
      <Link color="inherit" href="https://material-ui.com/">
        Your Website
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}

const useStyles = makeStyles((theme) => ({
  layout: {
    width: 'auto',
    marginLeft: theme.spacing(2),
    marginRight: theme.spacing(2),
    [theme.breakpoints.up(600 + theme.spacing(2) * 2)]: {
      width: 600,
      marginLeft: 'auto',
      marginRight: 'auto',
    },
  },
  paper: {
    marginTop: theme.spacing(3),
    marginBottom: theme.spacing(3),
    padding: theme.spacing(2),
    [theme.breakpoints.up(600 + theme.spacing(3) * 2)]: {
      marginTop: theme.spacing(6),
      marginBottom: theme.spacing(6),
      padding: theme.spacing(3),
    },
  },
  stepper: {
    padding: theme.spacing(3, 0, 5),
  },
  buttons: {
    display: 'flex',
    justifyContent: 'flex-end',
  },
  button: {
    marginTop: theme.spacing(3),
    marginLeft: theme.spacing(1),
  },
}));



export default function Checkout({ location }) {
  const classes = useStyles();

  const queryScopes = new URLSearchParams(location.search).get('scope');
  const scopes = queryScopes?.split(',')?.map((scope)=>{
    return {
      name : scope
      , value : true
    }
  })|| [];

  const [scopeValues, setScopeValues] = useState(scopes);
  const [scopeRefs, setScopeRefs] = useState(scopes);

  const formsubmit = (isApproval)=>{

    scopeRefs.forEach((el)=>{
      el.value = isApproval;
    });

    const rootForm = document.querySelector('#root');

    //외부 서버렌더링 + nonblock 조합이라 불가피하게 직접 dom 접근
    rootForm.action = '/oauth/authorize';
    rootForm.method = 'post';
    rootForm.submit();
  }

  return (
    <React.Fragment>
      <main className={classes.layout}>
        <Paper className={classes.paper}>
          <Typography component="h1" variant="h4" align="center">
            Checkout
          </Typography>
          <Informations />
          <input type='hidden' name='user_oauth_approval' value="true" />
          {
            scopeValues?.map((scope, i)=>{
              return (
                <input type="hidden" key={`scope-key-${i}`} name={`scope.${scope.name}`}
                value={scope.value}
                ref={(el)=>{ scopeRefs[i]=el }
                }
                />
              );
            })
          }
          <div className={classes.buttons}>
            <Button onClick={(e)=>{
              formsubmit(false)
            }} className={classes.button}>
                거부
            </Button>
            <Button
            variant="contained"
            color="primary"
            onClick={(e)=>{
              formsubmit(true)
            }}
            className={classes.button}
            >
            확인
            </Button>
          </div>
        </Paper>
        <Copyright />
      </main>
    </React.Fragment>
  );
}