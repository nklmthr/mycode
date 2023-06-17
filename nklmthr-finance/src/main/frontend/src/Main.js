import React from 'react';
import ReactDOM from 'react-dom/client';
import Root from "./App";
import AccountType from './AccountType';
import ErrorPage from './error-page';
import Institution from './Institution';
import Categories from './Categories';
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";

import "./index.css";
const router = createBrowserRouter([
    {
        path: "/",
        element: <Root />,
        errorElement: <ErrorPage />,
        children:[
            {
                path: "/accountTypes",
                element: <AccountType />,
            },
            {
                path: '/institutions',
                element: <Institution />
            },
            {
                path:'/Categories',
                element:<Categories />
            }
        ],
    }
]);

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);