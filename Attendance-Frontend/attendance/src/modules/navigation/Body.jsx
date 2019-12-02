import React from 'react';
import { Route, Switch } from 'react-router-dom';
import Helmet from 'react-helmet';

import CreateEntities from '../Entities/containers/CreateEntities/CreateEntities';
import Attendance from '../Attendance/containers/Attendance/Attendance';

const Body = () => (
    <div>
        <Helmet bodyAttributes={{style: 'background-color : #C0C0C0'}}/>
        <Switch>
            <Route exact path="/psbd/entities" component={CreateEntities} />
            <Route exact path="/psbd/attendance" component={Attendance} />

        </Switch>
    </div>
);

export default Body;